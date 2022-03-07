package com.cristiano.textsearch;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.repository.BookRepository;
import com.cristiano.textsearch.repository.MagazineRepository;
import com.cristiano.textsearch.repository.NotebookRepository;
import com.cristiano.textsearch.service.LuceneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.Calendar;
import java.util.stream.Stream;

@SpringBootApplication
public class TextSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextSearchApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BookRepository bookRepository) {
        return args -> {
            Stream.of("In Search of Lost Time", "Ulysses", "Don Quixote").forEach(title -> {
                Book book = new Book(title, "Cristiano");
                bookRepository.save(book);
            });
            bookRepository.findAll().forEach(book ->
                    System.out.println(book.getId() + " - " + book.getTitle() + " - " + book.getAuthor()));
        };
    }

    @Bean
    CommandLineRunner init2(MagazineRepository magazineRepository) {
        return args -> {
            Stream.of("Veja", "IstoE").forEach(name -> {
                Magazine magazine = new Magazine(name, new Date(Calendar.getInstance().getTime().getTime()));
                magazineRepository.save(magazine);
            });
            magazineRepository.findAll().forEach(magazine ->
                    System.out.println(magazine.getId() + " - " + magazine.getName() + " - " + magazine.getPublicationDate()));
        };
    }

    @Bean
    CommandLineRunner init3(NotebookRepository notebookRepository) {
        return args -> {
            Stream.of("Cristiano").forEach(owner -> {
                Notebook notebook = new Notebook(owner);
                notebookRepository.save(notebook);
            });
            notebookRepository.findAll().forEach(notebook ->
                    System.out.println(notebook.getId() + " - " + notebook.getOwner()));
        };
    }

    @Bean
    CommandLineRunner init4(LuceneService luceneService) {
        return args -> luceneService.indexFiles();
    }
}

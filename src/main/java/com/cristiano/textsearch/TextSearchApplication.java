package com.cristiano.textsearch;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class TextSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextSearchApplication.class, args);
	}

	@Bean
	CommandLineRunner init(BookRepository bookRepository) {
		return args -> {
			Stream.of("Title 1", "Title 2").forEach(title -> {
				Book book = new Book(title, "Cristiano");
				bookRepository.save(book);
			});
			bookRepository.findAll().forEach(book -> {
				System.out.println(book);
			});
		};
	}
}

package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.repository.BookRepository;
import com.cristiano.textsearch.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book addBook(String book, @RequestParam("file") MultipartFile file) throws IOException {
        Book savedBook = bookRepository.save(parseBook(book));
        updateFile(savedBook, file);
        return savedBook;
    }

    public Book updateBook(@PathVariable(value = "id") Long id, String book, @RequestParam("file") MultipartFile file)
            throws IOException {
        Book objectBook = parseBook(book);
        Book savedBook = bookRepository.findById(id).orElseGet(() -> {
            objectBook.setId(id);
            return bookRepository.save(objectBook);
        });
        savedBook.setTitle(objectBook.getTitle());
        savedBook.setAuthor(objectBook.getAuthor());
        savedBook = bookRepository.save(savedBook);
        updateFile(savedBook, file);
        return savedBook;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        File file = new File(FILE_DIRECTORY + id);
        file.delete();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void updateFile(BookShelfItem item, MultipartFile file) throws IOException {
        File myFile = new File(FILE_DIRECTORY + item.getId());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
    }

    private Book parseBook(String bookObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bookObject, Book.class);
    }
}

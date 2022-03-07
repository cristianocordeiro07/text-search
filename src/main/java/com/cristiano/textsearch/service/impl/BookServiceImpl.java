package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.repository.BookRepository;
import com.cristiano.textsearch.service.BookService;
import com.cristiano.textsearch.service.BookshelfItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookshelfItemService bookshelfItemService;

    public BookServiceImpl(BookRepository bookRepository, BookshelfItemService bookshelfItemService) {
        this.bookRepository = bookRepository;
        this.bookshelfItemService = bookshelfItemService;
    }

    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book addBook(String book, @RequestParam("file") MultipartFile file) throws IOException {
        Book savedBook = bookRepository.save(parseBook(book));
        bookshelfItemService.saveFile(savedBook, file);
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
        bookshelfItemService.saveFile(savedBook, file);
        return savedBook;
    }

    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        bookshelfItemService.deleteFile(id.toString());
    }

    private Book parseBook(String bookObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bookObject, Book.class);
    }
}

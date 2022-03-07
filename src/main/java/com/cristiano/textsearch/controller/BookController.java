package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public Book addBook(String book, @RequestParam("file") MultipartFile file) throws IOException {
        return bookService.addBook(book, file);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, String book, @RequestParam("file") MultipartFile file)
            throws IOException {
        return bookService.updateBook(id, book, file);
    }

    @DeleteMapping(value = "/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
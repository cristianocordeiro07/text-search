package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody Book book) {
        Book savedBook = bookRepository.findById(id).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });
        savedBook.setTitle(book.getTitle());
        savedBook.setAuthor(book.getAuthor());
        return bookRepository.save(savedBook);
    }

    @DeleteMapping(value = "/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
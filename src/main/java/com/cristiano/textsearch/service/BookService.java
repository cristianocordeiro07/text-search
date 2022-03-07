package com.cristiano.textsearch.service;

import com.cristiano.textsearch.entity.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book addBook(String book, @RequestParam("file") MultipartFile file) throws IOException;

    Book updateBook(@PathVariable(value = "id") Long id, String book, @RequestParam("file") MultipartFile file) throws IOException;

    void deleteBook(@PathVariable Long id);
}

package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.repository.BookshelfItemRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class BookshelfItemController {

    private final BookshelfItemRepository bookshelfItemRepository;

    private final Environment env;

    public BookshelfItemController(BookshelfItemRepository bookshelfItemRepository, Environment env) {
        this.bookshelfItemRepository = bookshelfItemRepository;
        this.env = env;
    }

    @GetMapping("/bookshelfItems/count")
    public long count() {
        return bookshelfItemRepository.count();
    }

    @GetMapping("/bookshelfItems/maxItems")
    public String maxItems() {
        return env.getProperty("bookshelf.max.items");
    }

    @GetMapping("/bookshelfItems/howManyMoreCanHold")
    public long howManyMoreCanHold() {

        //Differente between maximum items configured and total items in the database
        try {
            String maxItems = env.getProperty("bookshelf.max.items");
            return Long.parseLong(Objects.requireNonNull(maxItems)) - bookshelfItemRepository.count();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
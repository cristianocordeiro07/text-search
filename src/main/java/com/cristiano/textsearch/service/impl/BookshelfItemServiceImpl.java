package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.repository.BookshelfItemRepository;
import com.cristiano.textsearch.service.BookshelfItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BookshelfItemServiceImpl implements BookshelfItemService {

    private final BookshelfItemRepository bookshelfItemRepository;

    @Value("${bookshelf.max.items}")
    String MAX_ITEMS;

    public BookshelfItemServiceImpl(BookshelfItemRepository bookshelfItemRepository) {
        this.bookshelfItemRepository = bookshelfItemRepository;
    }

    public long count() {
        return bookshelfItemRepository.count();
    }

    public String maxItems() {
        return MAX_ITEMS;
    }

    public long howManyMoreCanHold() {

        //Differente between maximum items configured and total items in the database
        try {
            return Long.parseLong(Objects.requireNonNull(MAX_ITEMS)) - bookshelfItemRepository.count();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.service.BookshelfItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookshelfItemController {

    private final BookshelfItemService bookshelfItemService;


    public BookshelfItemController(BookshelfItemService bookshelfItemService) {
        this.bookshelfItemService = bookshelfItemService;
    }

    @GetMapping("/bookshelfItems/count")
    public long count() {
        return bookshelfItemService.count();
    }

    @GetMapping("/bookshelfItems/maxItems")
    public String maxItems() {
        return bookshelfItemService.maxItems();
    }

    @GetMapping("/bookshelfItems/howManyMoreCanHold")
    public long howManyMoreCanHold() {
        return bookshelfItemService.howManyMoreCanHold();
    }

    @GetMapping("/bookshelfItems/readPage")
    public String readPage(@PathVariable Long id) {
        return "";
    }
}
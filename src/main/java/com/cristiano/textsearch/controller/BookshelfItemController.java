package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.service.BookshelfItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.List;

@RestController
public class BookshelfItemController {

    Logger logger = LoggerFactory.getLogger(BookshelfItemController.class);

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
    public String readPage(Long itemId, Long pageNumber) throws PrinterException, IOException {
        return bookshelfItemService.readPage(itemId, pageNumber);
    }

    @GetMapping("/bookshelfItems/searchItems")
    public List<BookShelfItem> searchByText(String text) {
        try {
            return bookshelfItemService.searchByText(text);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
}
package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.service.BookshelfItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
public class BookshelfItemController {

    private final BookshelfItemService bookshelfItemService;

    public BookshelfItemController(BookshelfItemService bookshelfItemService) {
        this.bookshelfItemService = bookshelfItemService;
    }

    @GetMapping("/bookshelfItems/count")
    public ResponseEntity<Long> count() {
        try {
            return new ResponseEntity<>(bookshelfItemService.count(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookshelfItems/maxItems")
    public ResponseEntity<String> maxItems() {
        try {
            return new ResponseEntity<>(bookshelfItemService.maxItems(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookshelfItems/howManyMoreCanHold")
    public ResponseEntity<Long> howManyMoreCanHold() {
        try {
            return new ResponseEntity<>(bookshelfItemService.howManyMoreCanHold(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookshelfItems/readPage")
    public ResponseEntity<String> readPage(Long itemId, Long pageNumber) {
        try {
            return new ResponseEntity<>(bookshelfItemService.readPage(itemId, pageNumber), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookshelfItems/searchItems")
    public ResponseEntity<List<BookShelfItem>> searchByText(String text) {
        try {
            return new ResponseEntity<>(bookshelfItemService.searchByText(text), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
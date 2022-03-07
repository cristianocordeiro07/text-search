package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.service.NotebookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
public class NotebookController {

    private final NotebookService notebookService;

    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/notebooks")
    public ResponseEntity<List<Notebook>> getNotebooks() {
        try {
            return new ResponseEntity<>(notebookService.getNotebooks(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/notebooks")
    public ResponseEntity<Notebook> addNotebook(String notebook, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(notebookService.addNotebook(notebook, file), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notebooks/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable(value = "id") Long id, String notebook, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(notebookService.updateNotebook(id, notebook, file), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/notebooks/{id}")
    public ResponseEntity<Long> deleteNotebook(@PathVariable Long id) {
        try {
            notebookService.deleteNotebook(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
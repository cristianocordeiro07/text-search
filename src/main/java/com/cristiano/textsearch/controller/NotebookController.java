package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.service.NotebookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class NotebookController {

    private final NotebookService notebookService;

    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/notebooks")
    public List<Notebook> getNotebooks() {
        return notebookService.getNotebooks();
    }

    @PostMapping("/notebooks")
    public Notebook addNotebook(String notebook, @RequestParam("file") MultipartFile file) throws IOException {
        return notebookService.addNotebook(notebook, file);
    }

    @PutMapping("/notebooks/{id}")
    public Notebook updateNotebook(@PathVariable(value = "id") Long id, String notebook, @RequestParam("file") MultipartFile file)
            throws IOException {
        return notebookService.updateNotebook(id, notebook, file);
    }

    @DeleteMapping(value = "/notebooks/{id}")
    public void deleteNotebook(@PathVariable Long id) {
        notebookService.deleteNotebook(id);
    }
}
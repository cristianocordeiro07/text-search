package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.repository.NotebookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotebookController {

    private final NotebookRepository notebookRepository;

    public NotebookController(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    @GetMapping("/notebooks")
    public List<Notebook> getNotebooks() {
        return (List<Notebook>) notebookRepository.findAll();
    }

    @PostMapping("/notebooks")
    public Notebook addNotebook(@RequestBody Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    @PutMapping("/notebooks/{id}")
    public Notebook updateNotebook(@PathVariable(value = "id") Long id, @RequestBody Notebook notebook) {
        Notebook savedNotebook = notebookRepository.findById(id).orElseGet(() -> {
            notebook.setId(id);
            return notebookRepository.save(notebook);
        });
        savedNotebook.setOwner(notebook.getOwner());
        return notebookRepository.save(savedNotebook);
    }

    @DeleteMapping(value = "/notebooks/{id}")
    public void deleteNotebook(@PathVariable Long id) {
        notebookRepository.deleteById(id);
    }
}
package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.repository.NotebookRepository;
import com.cristiano.textsearch.service.BookshelfItemService;
import com.cristiano.textsearch.service.NotebookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;

    private final BookshelfItemService bookshelfItemService;

    public NotebookServiceImpl(NotebookRepository notebookRepository, BookshelfItemService bookshelfItemService) {
        this.notebookRepository = notebookRepository;
        this.bookshelfItemService = bookshelfItemService;
    }

    public List<Notebook> getNotebooks() {
        return (List<Notebook>) notebookRepository.findAll();
    }

    public Notebook addNotebook(String notebook, @RequestParam("file") MultipartFile file) throws IOException {
        Notebook savedNotebook = notebookRepository.save(parseObject(notebook));
        bookshelfItemService.updateFile(savedNotebook, file);
        return savedNotebook;
    }

    public Notebook updateNotebook(@PathVariable(value = "id") Long id, String notebook, @RequestParam("file") MultipartFile file)
            throws IOException {
        Notebook objectNotebook = parseObject(notebook);
        Notebook savedNotebook = notebookRepository.findById(id).orElseGet(() -> {
            objectNotebook.setId(id);
            return notebookRepository.save(objectNotebook);
        });
        savedNotebook.setOwner(objectNotebook.getOwner());
        savedNotebook = notebookRepository.save(savedNotebook);
        bookshelfItemService.updateFile(savedNotebook, file);
        return savedNotebook;
    }

    public void deleteNotebook(@PathVariable Long id) {
        notebookRepository.deleteById(id);
        bookshelfItemService.deleteFile(id.toString());
    }

    private Notebook parseObject(String object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(object, Notebook.class);
    }
}

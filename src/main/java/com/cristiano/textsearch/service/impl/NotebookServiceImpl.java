package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.entity.Notebook;
import com.cristiano.textsearch.repository.NotebookRepository;
import com.cristiano.textsearch.service.NotebookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    @Value("${file-upload-dir}")
    String FILE_DIRECTORY;

    private final NotebookRepository notebookRepository;

    public NotebookServiceImpl(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public List<Notebook> getNotebooks() {
        return (List<Notebook>) notebookRepository.findAll();
    }

    public Notebook addNotebook(String notebook, @RequestParam("file") MultipartFile file) throws IOException {
        Notebook savedNotebook = notebookRepository.save(parseObject(notebook));
        updateFile(savedNotebook, file);
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
        updateFile(savedNotebook, file);
        return savedNotebook;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteNotebook(@PathVariable Long id) {
        notebookRepository.deleteById(id);
        File file = new File(FILE_DIRECTORY + id);
        file.delete();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void updateFile(BookShelfItem item, MultipartFile file) throws IOException {
        File myFile = new File(FILE_DIRECTORY + item.getId());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
    }

    private Notebook parseObject(String object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(object, Notebook.class);
    }
}

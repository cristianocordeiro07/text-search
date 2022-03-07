package com.cristiano.textsearch.service;

import com.cristiano.textsearch.entity.Notebook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NotebookService {

    List<Notebook> getNotebooks();

    Notebook addNotebook(String notebook, @RequestParam("file") MultipartFile file) throws IOException;

    Notebook updateNotebook(@PathVariable(value = "id") Long id, String notebook, @RequestParam("file") MultipartFile file) throws IOException;

    void deleteNotebook(@PathVariable Long id);
}

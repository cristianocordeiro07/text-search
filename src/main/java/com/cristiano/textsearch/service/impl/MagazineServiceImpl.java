package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.repository.MagazineRepository;
import com.cristiano.textsearch.service.MagazineService;
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
public class MagazineServiceImpl implements MagazineService {

    @Value("${file-upload-dir}")
    String FILE_DIRECTORY;

    private final MagazineRepository magazineRepository;

    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    public List<Magazine> getMagazines() {
        return (List<Magazine>) magazineRepository.findAll();
    }

    public Magazine addMagazine(String magazine, @RequestParam("file") MultipartFile file) throws IOException {
        Magazine savedMagazine = magazineRepository.save(parseObject(magazine));
        updateFile(savedMagazine, file);
        return savedMagazine;
    }

    public Magazine updateMagazine(@PathVariable(value = "id") Long id, String magazine, @RequestParam("file") MultipartFile file)
            throws IOException {
        Magazine objectMagazine = parseObject(magazine);
        Magazine savedMagazine = magazineRepository.findById(id).orElseGet(() -> {
            objectMagazine.setId(id);
            return magazineRepository.save(objectMagazine);
        });
        savedMagazine.setName(objectMagazine.getName());
        savedMagazine.setPublicationDate(objectMagazine.getPublicationDate());
        savedMagazine = magazineRepository.save(savedMagazine);
        updateFile(savedMagazine, file);
        return savedMagazine;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteMagazine(@PathVariable Long id) {
        magazineRepository.deleteById(id);
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

    private Magazine parseObject(String object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(object, Magazine.class);
    }
}

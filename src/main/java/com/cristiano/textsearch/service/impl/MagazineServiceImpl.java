package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.repository.MagazineRepository;
import com.cristiano.textsearch.service.BookshelfItemService;
import com.cristiano.textsearch.service.MagazineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepository magazineRepository;

    private final BookshelfItemService bookshelfItemService;

    public MagazineServiceImpl(MagazineRepository magazineRepository, BookshelfItemService bookshelfItemService) {
        this.magazineRepository = magazineRepository;
        this.bookshelfItemService = bookshelfItemService;
    }

    public List<Magazine> getMagazines() {
        return (List<Magazine>) magazineRepository.findAll();
    }

    public Magazine addMagazine(String magazine, @RequestParam("file") MultipartFile file) throws IOException {
        Magazine savedMagazine = magazineRepository.save(parseObject(magazine));
        bookshelfItemService.saveFile(savedMagazine, file);
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
        bookshelfItemService.saveFile(savedMagazine, file);
        return savedMagazine;
    }

    public void deleteMagazine(@PathVariable Long id) {
        magazineRepository.deleteById(id);
        bookshelfItemService.deleteFile(id.toString());
    }

    private Magazine parseObject(String object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(object, Magazine.class);
    }
}

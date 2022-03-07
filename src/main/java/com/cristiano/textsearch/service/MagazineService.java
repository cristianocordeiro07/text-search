package com.cristiano.textsearch.service;

import com.cristiano.textsearch.entity.Magazine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MagazineService {

    List<Magazine> getMagazines();

    Magazine addMagazine(String magazine, @RequestParam("file") MultipartFile file) throws IOException;

    Magazine updateMagazine(@PathVariable(value = "id") Long id, String magazine, @RequestParam("file") MultipartFile file) throws IOException;

    void deleteMagazine(@PathVariable Long id);
}

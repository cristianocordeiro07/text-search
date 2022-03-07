package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.service.MagazineService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/magazines")
    public List<Magazine> getMagazines() {
        return magazineService.getMagazines();
    }

    @PostMapping("/magazines")
    public Magazine addMagazine(String magazine, @RequestParam("file") MultipartFile file) throws IOException {
        return magazineService.addMagazine(magazine, file);
    }

    @PutMapping("/magazines/{id}")
    public Magazine updateMagazine(@PathVariable(value = "id") Long id, String magazine, @RequestParam("file") MultipartFile file)
            throws IOException {
        return magazineService.updateMagazine(id, magazine, file);
    }

    @DeleteMapping(value = "/magazines/{id}")
    public void deleteMagazine(@PathVariable Long id) {
        magazineService.deleteMagazine(id);
    }
}
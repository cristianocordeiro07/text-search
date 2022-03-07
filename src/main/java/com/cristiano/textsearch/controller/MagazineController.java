package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.service.MagazineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/magazines")
    public ResponseEntity<List<Magazine>> getMagazines() {
        try {
            return new ResponseEntity<>(magazineService.getMagazines(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/magazines")
    public ResponseEntity<Magazine> addMagazine(String magazine, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(magazineService.addMagazine(magazine, file), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/magazines/{id}")
    public ResponseEntity<Magazine> updateMagazine(@PathVariable(value = "id") Long id, String magazine, @RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(magazineService.updateMagazine(id, magazine, file), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/magazines/{id}")
    public ResponseEntity<Long> deleteMagazine(@PathVariable Long id) {
        try {
            magazineService.deleteMagazine(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
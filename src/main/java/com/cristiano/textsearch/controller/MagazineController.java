package com.cristiano.textsearch.controller;

import com.cristiano.textsearch.entity.Magazine;
import com.cristiano.textsearch.repository.MagazineRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MagazineController {

    private final MagazineRepository magazineRepository;

    public MagazineController(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @GetMapping("/magazines")
    public List<Magazine> getMagazines() {
        return (List<Magazine>) magazineRepository.findAll();
    }

    @PostMapping("/magazines")
    public Magazine addMagazine(@RequestBody Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @PutMapping("/magazines/{id}")
    public Magazine updateMagazine(@PathVariable(value = "id") Long id, @RequestBody Magazine magazine) {
        Magazine savedMagazine = magazineRepository.findById(id).orElseGet(() -> {
            magazine.setId(id);
            return magazineRepository.save(magazine);
        });
        savedMagazine.setName(magazine.getName());
        savedMagazine.setPublicationDate(magazine.getPublicationDate());
        return magazineRepository.save(savedMagazine);
    }

    @DeleteMapping(value = "/magazines/{id}")
    public void deleteMagazine(@PathVariable Long id) {
        magazineRepository.deleteById(id);
    }
}
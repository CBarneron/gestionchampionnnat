package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Championship;
import com.ipi.gestionchampionnat.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    @Autowired
    private ChampionshipRepository championshipRepository;

    @GetMapping
    public List<Championship> getAllChampionships() {
        return championshipRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Championship> createChampionship(@RequestBody Championship championship) {
        championshipRepository.save(championship);
        return  new ResponseEntity<>(championship, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Championship getChampionshipById(@PathVariable Long id) {
        return championshipRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Championship> updateChampionship(@PathVariable Long id, @RequestBody Championship championship) {
        championship.setId(id);
        championshipRepository.save(championship);
        return new ResponseEntity<>(championship, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable Long id) {
        championshipRepository.deleteById(id);
    }
}
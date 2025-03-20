package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Championship;
import com.ipi.gestionchampionnat.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Championship createChampionship(@RequestBody Championship championship) {
        return championshipRepository.save(championship);
    }

    @GetMapping("/{id}")
    public Championship getChampionshipById(@PathVariable Long id) {
        return championshipRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Championship updateChampionship(@PathVariable Long id, @RequestBody Championship championship) {
        championship.setId(id);
        return championshipRepository.save(championship);
    }

    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable Long id) {
        championshipRepository.deleteById(id);
    }
}
package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Day;
import com.ipi.gestionchampionnat.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @GetMapping
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Day> createDay(@RequestBody Day day) {
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable Long id) {
        return dayRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable Long id, @RequestBody Day day) {
        day.setId(id);
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable Long id) {
        dayRepository.deleteById(id);
    }
}
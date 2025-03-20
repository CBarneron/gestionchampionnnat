package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Day;
import com.ipi.gestionchampionnat.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Days")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @GetMapping
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    @PostMapping
    public Day createDay(@RequestBody Day Day) {
        return dayRepository.save(Day);
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable Long id) {
        return dayRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Day updateDay(@PathVariable Long id, @RequestBody Day Day) {
        Day.setId(id);
        return dayRepository.save(Day);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable Long id) {
        dayRepository.deleteById(id);
    }
}
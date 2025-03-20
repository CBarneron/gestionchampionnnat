package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Day;

@Repository
public interface DayRepository extends CrudRepository<Day,Long> {

    @Override
    List<Day> findAll();
    
}

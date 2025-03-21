package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Day;

@Repository
public interface DayRepository extends  JpaRepository<Day,Long> {

    @Override
    List<Day> findAll();
    
}

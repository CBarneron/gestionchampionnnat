package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Team;

@Repository
public interface TeamRepository extends  JpaRepository<Team,Long> {

    @Override
    List<Team> findAll();
    
}
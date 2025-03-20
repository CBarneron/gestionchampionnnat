package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {

    @Override
    List<Team> findAll();
    
}
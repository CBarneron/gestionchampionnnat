package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Game;

@Repository
public interface GameRepository extends  JpaRepository<Game,Long> {

    @Override
    List<Game> findAll();

}

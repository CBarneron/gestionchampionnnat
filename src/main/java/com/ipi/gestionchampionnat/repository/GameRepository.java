package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.Game;
import com.ipi.gestionchampionnat.models.Team;

@Repository
public interface GameRepository extends  JpaRepository<Game,Long> {

    @Override
    List<Game> findAll();
    List<Game> findByTeam1OrTeam2(Team team1, Team team2);
}

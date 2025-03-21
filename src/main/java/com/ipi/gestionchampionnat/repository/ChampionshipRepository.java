package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipi.gestionchampionnat.models.Championship;

public interface ChampionshipRepository extends JpaRepository<Championship,Long> {

    @Override
    List<Championship> findAll();

}

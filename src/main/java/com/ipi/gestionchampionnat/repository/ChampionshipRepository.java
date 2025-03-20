package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ipi.gestionchampionnat.models.Championship;

public interface ChampionshipRepository extends CrudRepository<Championship,Long> {

    @Override
    List<Championship> findAll();

}

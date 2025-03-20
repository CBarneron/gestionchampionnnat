package com.ipi.gestionchampionnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipi.gestionchampionnat.models.User;

@Repository
public interface UserRepository extends  JpaRepository<User,Long> {

    @Override
    List<User> findAll();
}
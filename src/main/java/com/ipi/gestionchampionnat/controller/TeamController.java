package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Championship;
import com.ipi.gestionchampionnat.models.Game;
import com.ipi.gestionchampionnat.models.Team;
import com.ipi.gestionchampionnat.repository.ChampionshipRepository;
import com.ipi.gestionchampionnat.repository.GameRepository;
import com.ipi.gestionchampionnat.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
         teamRepository.save(team);
         return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    //TODO  a Finir
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team.setId(id);
        teamRepository.save(team);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }


    // TODO a finir
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
    }

    @GetMapping("/championship/{championshipId}")
    public List<Team> getTeamsByChampionshipId(@PathVariable Long championshipId) {
        // Fetch teams by championship ID or return an empty list if none are found
        return teamRepository.findByChampionships_Id(championshipId);
    }

    @PostMapping("/championship/{championshipId}")
    public ResponseEntity<Team> ajouterTeamChampionat(@PathVariable(value = "championshipId") Long championshipId, @RequestBody Team teamRequest) {
        Team team = championshipRepository.findById(championshipId).map(championship -> {
            long teamId = teamRequest.getId();

            if (teamId != 0) {
                Team newTeam = teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'équipe avec l'Id : " + teamId));
                championship.addTeam(newTeam);
                championshipRepository.save(championship);
                return newTeam;
            } else {
                if (teamRequest.getId() != 0 && teamRepository.existsById(teamRequest.getId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Une équipe avec cet ID existe déjà");
                }
                championship.addTeam(teamRequest);
                return teamRepository.save(teamRequest);
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de championnat avec l'Id : " + championshipId));

        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }
}
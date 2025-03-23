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

    // Mettre à jour un résultat
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team.setId(team.getId());
        teamRepository.save(team);

        return new ResponseEntity<>(team, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        try {
            Team team = teamRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Équipe non trouvée"));
    
            team.getChampionships().forEach(championship -> championship.getTeams().remove(team));
            team.getChampionships().clear();
            teamRepository.save(team);
    
            teamRepository.deleteById(id);
            return ResponseEntity.ok("Équipe supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'équipe : " + e.getMessage());
        }
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
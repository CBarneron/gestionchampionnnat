package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.models.Day;
import com.ipi.gestionchampionnat.models.Game;
import com.ipi.gestionchampionnat.models.Team;
import com.ipi.gestionchampionnat.repository.GameRepository;
import com.ipi.gestionchampionnat.repository.TeamRepository;

import jakarta.validation.Valid;

import com.ipi.gestionchampionnat.repository.DayRepository;
import com.ipi.gestionchampionnat.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DayRepository dayRepository;
    
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

    // Récupérer la liste des résultats
    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

  // Récupérer les résultats d'un championnat
    @GetMapping("/championship/{championshipId}/result")
    public ResponseEntity<List<Game>> getGamesByChampionship(@PathVariable Long championshipId) {
        List<Game> allGames = gameRepository.findAll();
        List<Game> filtered = allGames.stream()
                .filter(game -> game.getDay().getChampionshipId().equals(championshipId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    // Récupérer les résultats d'une journée
    @GetMapping("/day/{dayId}/result")
    public ResponseEntity<List<Game>> getGamesByDay(@PathVariable Long dayId) {
        List<Game> filtered = gameRepository.findAll().stream()
                .filter(game -> game.getDay().getId() == dayId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    // Récupérer un résultat suivant son id
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    // Créer un résultat pour une journée
    @PostMapping("/day/{dayId}/create")
    public ResponseEntity<?> createGame(@PathVariable Long dayId, @Valid @RequestBody Game game) {
        if (game.getTeam1() == null || game.getTeam1().getId() == null) {
            return ResponseEntity.badRequest().body("Team1 is required and must have a valid ID");
        }
        if (game.getTeam2() == null || game.getTeam2().getId() == null) {
            return ResponseEntity.badRequest().body("Team2 is required and must have a valid ID");
        }

        // Fetch the Day entity from the database
        Optional<Day> dayOpt = dayRepository.findById(dayId);
        if (dayOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Day not found with ID: " + dayId);
        }

        // Fetch the Team entities from the database
        Optional<Team> team1Opt = teamRepository.findById(game.getTeam1().getId());
        Optional<Team> team2Opt = teamRepository.findById(game.getTeam2().getId());

        if (team1Opt.isEmpty() || team2Opt.isEmpty()) {
            return ResponseEntity.badRequest().body("Team1 or Team2 not found");
        }

        // Assign the managed entities to the Game
        game.setDay(dayOpt.get());
        game.setTeam1(team1Opt.get());
        game.setTeam2(team2Opt.get());

        // Save the Game entity
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    // Mettre à jour un résultat
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        game.setId(id);
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    // Supprimer un résultat
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }
}

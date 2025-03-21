package com.ipi.gestionchampionnat.config;

import java.time.LocalDate;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ipi.gestionchampionnat.models.Championship;
import com.ipi.gestionchampionnat.models.Day;
import com.ipi.gestionchampionnat.models.Game;
import com.ipi.gestionchampionnat.models.Team;
import com.ipi.gestionchampionnat.models.User;
import com.ipi.gestionchampionnat.repository.ChampionshipRepository;
import com.ipi.gestionchampionnat.repository.DayRepository;
import com.ipi.gestionchampionnat.repository.GameRepository;
import com.ipi.gestionchampionnat.repository.TeamRepository;
import com.ipi.gestionchampionnat.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner initData(
            ChampionshipRepository championshipRepository,
            TeamRepository teamRepository,
            DayRepository dayRepository,
            GameRepository gameRepository,
            UserRepository userRepository) {

                Logger log = LoggerFactory.getLogger(DataInitializer.class);
        return args -> {
            log.info("Start of initialisation");
            // Initialisation des équipes
            Team team1 = new Team("Team A", LocalDate.of(2020, 1, 1));
            Team team2 = new Team("Team B", LocalDate.of(2021, 2, 1));
            Team team3 = new Team("Team C", LocalDate.of(2022, 3, 1));
            Team team4 = new Team("Team D", LocalDate.of(2023, 4, 1));
            Team team5 = new Team("Team E", LocalDate.of(2024, 5, 1));
            teamRepository.saveAll(Set.of(team1, team2, team3, team4, team5));
            log.info("Teams found with findAll():");
            log.info("-------------------------------");
            // Initialisation des championnats
            Championship championship1 = new Championship("Championship 1", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), 3, 0, 1);
            Championship championship2 = new Championship("Championship 2", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 3, 0, 1);
            championship1.getTeams().addAll(Set.of(team1, team2, team3));
            championship2.getTeams().addAll(Set.of(team4, team5));
            championshipRepository.saveAll(Set.of(championship1, championship2));
            log.info("Championships found with findAll():");
            log.info("-------------------------------");
            // Initialisation des journées
            Day day1 = new Day();
            day1.setNumber("Day 1");
            day1.setChampionshipId(championship1.getId());
            Day day2 = new Day();
            day2.setNumber("Day 2");
            day2.setChampionshipId(championship1.getId());
            Day day3 = new Day();
            day3.setNumber("Day 3");
            day3.setChampionshipId(championship2.getId());
    
            Day day4 = new Day();
            day4.setNumber("Day 4");
            day4.setChampionshipId(championship2.getId());

            Day day5 = new Day();
            day5.setNumber("Day 5");
            day5.setChampionshipId(championship2.getId());

            dayRepository.saveAll(Set.of(day1, day2, day3, day4, day5));
            log.info("Days found with findAll():");
            log.info("-------------------------------");
            // Initialisation des jeux
            Game game1 = new Game();
            game1.setNumber("Game 1");
            game1.setChampionshipId(championship1.getId());
            game1.setTeam1(team1);
            game1.setTeam2(team2);
            game1.setDay(day1);
            
            Game game2 = new Game();
            game2.setNumber("Game 2");
            game2.setChampionshipId(championship1.getId());
            game2.setTeam1(team3);
            game2.setTeam2(team1);
            game2.setDay(day2);

            Game game3 = new Game();
            game3.setNumber("Game 3");
            game3.setChampionshipId(championship2.getId());
            game3.setTeam1(team4);
            game3.setTeam2(team5);
            game3.setDay(day3);

            Game game4 = new Game();
            game4.setNumber("Game 4");
            game4.setChampionshipId(championship2.getId());
            game4.setTeam1(team5);
            game4.setTeam2(team4);
            game4.setDay(day4);

            Game game5 = new Game();
            game5.setNumber("Game 5");
            game5.setChampionshipId(championship2.getId());
            game5.setTeam1(team4);
            game5.setTeam2(team3);
            game5.setDay(day5);

            gameRepository.saveAll(Set.of(game1, game2, game3, game4, game5));
            log.info("Games found with findAll():");
            log.info("-------------------------------");
            // Initialisation des utilisateurs
            User user1 = new User("John", "Doe", "john.doe@example.com", passwordEncoder.encode("password123"), LocalDate.of(2020, 1, 1));
            User user2 = new User("Jane", "Smith", "jane.smith@example.com", passwordEncoder.encode("password123"), LocalDate.of(2021, 2, 1));
            User user3 = new User("Alice", "Johnson", "alice.johnson@example.com", passwordEncoder.encode("password123"), LocalDate.of(2022, 3, 1));
            User user4 = new User("Bob", "Brown", "bob.brown@example.com", passwordEncoder.encode("password123"), LocalDate.of(2023, 4, 1));
            User user5 = new User("Charlie", "Davis", "charlie.davis@example.com", passwordEncoder.encode("password123"), LocalDate.of(2024, 5, 1));
            userRepository.saveAll(Set.of(user1, user2, user3, user4, user5));
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            log.info("End of initialisation");
        };
    }
}
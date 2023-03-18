package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamPlayersDto;
import org.example.model.dto.TeamReadDto;
import org.example.model.dto.TeamUpdDto;
import org.example.service.TeamService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping(value = "/teams")
    public List<TeamReadDto> readAll(
            @RequestParam(value = "typeSport", required = false) String typeSport,
            @RequestParam(value = "startPeriod", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startPeriod,
            @RequestParam(value = "finishPeriod", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate finishPeriod) throws ParseException {
        return teamService.readAll(typeSport, startPeriod, finishPeriod);
    }

    @GetMapping(value = "/team-players/{id}")
    public List<TeamPlayersDto> read(@PathVariable(name = "id") Long id,
                                     @RequestParam(value = "role", required = false) String role) {
        return teamService.readTeam(id, role);
    }

    @PostMapping(value = "/teams")
    public void create(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @PutMapping(value = "/teams/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody TeamUpdDto teamUpdDto) {
        teamService.update(id, teamUpdDto);
    }

    @DeleteMapping(value = "/teams/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        teamService.deleteTeam(id);
    }
}

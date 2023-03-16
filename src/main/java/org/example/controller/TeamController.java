package org.example.controller;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;
import org.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/team")
    public List<TeamDto> readAll(
            @RequestParam(value = "typeSport", required = false) String typeSport,
            @RequestParam(value = "startPeriod", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date startPeriod,
            @RequestParam(value = "finishPeriod", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date finishPeriod) throws ParseException {
        return teamService.readAll(typeSport, startPeriod, finishPeriod);
    }

    @GetMapping(value = "/team/{id}")
    public List<PlayerTeamDto> read(@PathVariable(name = "id") Long id,
                                    @RequestParam(value = "role", required = false) String role) {
        return teamService.readTeam(id, role);
    }

    @PostMapping(value = "/team")
    public void create(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @PutMapping(value = "/team/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody TeamUpdDto teamUpdDto) {
        teamService.update(id, teamUpdDto);
    }

    @DeleteMapping(value = "/team/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        teamService.deleteTeam(id);
    }
}

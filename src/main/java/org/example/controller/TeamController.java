package org.example.controller;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;
import org.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "typeSport") String typeSport,
            @RequestParam(value = "startPeriod") Date startPeriod,
            @RequestParam(value = "finishPeriod") Date finishPeriod) {
        return teamService.readAll(typeSport, startPeriod, finishPeriod);
    }

    @GetMapping(value = "/team/{id}")
    public List<PlayerTeamDto> read(@PathVariable(name = "id") Long id,
                                    @RequestParam(value = "role") String role) {
        return teamService.readTeam(id, role);
    }

    @PostMapping(value = "/team")
    public void create(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @PutMapping(value="/team/{id}")
    public void update(@PathVariable(name="id") Long id, @RequestBody TeamUpdDto teamUpdDto) {
        teamService.updateTeam(teamUpdDto,id);
    }

    @DeleteMapping(value="/cities/{id}")
    public void delete(@PathVariable(name="id") Long id){
        teamService.deleteTeam(id);
    }
}

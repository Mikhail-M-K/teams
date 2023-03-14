package org.example.controller;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;
import org.example.service.PlayerTeamService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerTeamController {

    private final PlayerTeamService playerTeamService;


    public PlayerTeamController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping(value = "/player")
    public void create(@RequestBody PlayerTeamDto playerTeamDto) {
        playerTeamService.createPLayer(playerTeamDto);
    }

    @PutMapping(value = "/team/{id}")
    public void update(@PathVariable(name="id") Long id, @RequestBody PlayerTeamUpdDto teamUpdUpdDto) {
        playerTeamService.updatePlayer(teamUpdUpdDto,id);
    }

    @PostMapping(value = "/team/{id}")
    public void transfer(@PathVariable(name="id") Long id, @RequestParam(value = "idTeam") Long idTeam) {
        playerTeamService.transferPlayer(id, idTeam);
    }

    @DeleteMapping(value="/team/{id}")
    public void delete(@PathVariable(name="id") Long id){
        playerTeamService.deletePlayer(id);
    }
}

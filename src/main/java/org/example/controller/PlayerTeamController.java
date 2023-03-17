package org.example.controller;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;
import org.example.service.PlayerTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerTeamController {

    private final PlayerTeamService playerTeamService;

    @Autowired
    public PlayerTeamController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping(value = "/players")
    public void create(@RequestBody PlayerTeamDto playerTeamDto) {
        playerTeamService.createPLayer(playerTeamDto);
    }

    @PutMapping(value = "/players/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody PlayerTeamUpdDto playerTeamUpdDto) {
        playerTeamService.update(id, playerTeamUpdDto);
    }

    @PostMapping(value = "/players-transfer/{id}")
    public void transfer(@PathVariable(name = "id") Long id, @RequestParam(value = "idTeam") Long idTeam) {
        playerTeamService.transferPlayer(id, idTeam);
    }

    @DeleteMapping(value = "/players/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        playerTeamService.deletePlayer(id);
    }
}

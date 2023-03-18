package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.PlayerDto;
import org.example.model.dto.PlayerUpdDto;
import org.example.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping(value = "/players")
    public void create(@RequestBody PlayerDto playerDto) {
        playerService.createPLayer(playerDto);
    }

    @PutMapping(value = "/players/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody PlayerUpdDto playerUpdDto) {
        playerService.update(id, playerUpdDto);
    }

    @PostMapping(value = "/players-transfer/{id}")
    public void transfer(@PathVariable(name = "id") Long id, @RequestParam(value = "idTeam") Long idTeam) {
        playerService.transferPlayer(id, idTeam);
    }

    @DeleteMapping(value = "/players/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        playerService.deletePlayer(id);
    }
}

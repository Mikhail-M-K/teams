package org.example.service;

import org.example.model.dto.PlayerDto;
import org.example.model.dto.PlayerUpdDto;

public interface PlayerService {

    void createPLayer(PlayerDto playerDto);

    void transferPlayer(Long id, Long idTeam);

    void deletePlayer(Long id);

    void update(Long id, PlayerUpdDto playerUpdDto);
}

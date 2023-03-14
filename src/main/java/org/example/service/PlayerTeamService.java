package org.example.service;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;

public interface PlayerTeamService {

    void createPLayer(PlayerTeamDto playerTeamDto);

    void transferPlayer(Long id, Long idTeam);

    void deletePlayer(Long id);

    void update(Long id, PlayerTeamUpdDto playerTeamUpdDto);
}

package org.example.service;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;

public interface PlayerTeamService {

    void createPLayer(PlayerTeamDto playerTeamDto);

    void transferPlayer(Long Id, Long IdTeam);

    void deletePlayer(Long Id);

    void updatePlayer(PlayerTeamUpdDto playerTeamUpdDto, Long id);
}

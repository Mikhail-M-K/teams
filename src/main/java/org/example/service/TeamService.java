package org.example.service;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;

import java.util.Date;
import java.util.List;

public interface TeamService {
    List<TeamDto> readAll(String typeSport, Date startPeriod, Date finishPeriod);

    List<PlayerTeamDto> readTeam(Long id, String role);

    void createTeam(TeamDto teamDto);

    void deleteTeam(Long id);

    void updateTeam(TeamUpdDto teamUpdDto, Long id);
}

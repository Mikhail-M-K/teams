package org.example.service;

import org.example.model.dto.TeamPlayersDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TeamService {
    List<TeamDto> readAll(String typeSport, Date startPeriod, Date finishPeriod) throws ParseException;

    List<TeamPlayersDto> readTeam(Long id, String role);

    void createTeam(TeamDto teamDto);

    void deleteTeam(Long id);

    void update(Long id, TeamUpdDto teamUpdDto);
}

package org.example.service;

import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    @Override
    public List<TeamDto> readAll(String typeSport, Date startPeriod, Date finishPeriod) {
        return null;
    }

    @Override
    public List<PlayerTeamDto> readTeam(Long id, String role) {
        return null;
    }

    @Override
    public void createTeam(TeamDto teamDto) {

    }

    @Override
    public void deleteTeam(Long Id) {

    }

    @Override
    public void updateTeam(TeamUpdDto teamUpdDto, Long id) {

    }
}

package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Player;
import org.example.model.Team;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamPlayersDto;
import org.example.model.dto.TeamReadDto;
import org.example.model.dto.TeamUpdDto;
import org.example.repos.PlayerRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    PlayerRepo playerRepo;

    TeamRepo teamRepo;

    @Transactional
    @Override
    public List<TeamReadDto> readAll(String typeSport, LocalDate startPeriod, LocalDate finishPeriod) {
        if (startPeriod == null && finishPeriod == null && (typeSport == null || typeSport.isEmpty())) {
            return teamRepo.findAll().stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());
        } else if (startPeriod == null && finishPeriod == null) {
            return teamRepo.findAllBySportType(typeSport)
                    .stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());
        } else {
            if (finishPeriod == null) {
                finishPeriod = LocalDate.now();
            }
            if (startPeriod == null) {
                startPeriod = LocalDate.of(1000, 1, 1);
            }
            if ((typeSport == null || typeSport.isEmpty()) && (startPeriod != null || finishPeriod != null)) {
                return teamRepo.findAllByFoundationDateBetween(startPeriod, finishPeriod)
                        .stream()
                        .map(this::convertTeamToDto)
                        .collect(Collectors.toList());
            } else {
                return teamRepo.findAllBySportTypeAndFoundationDateBetween(typeSport, startPeriod, finishPeriod)
                        .stream()
                        .map(this::convertTeamToDto)
                        .collect(Collectors.toList());
            }
        }
    }

    private TeamReadDto convertTeamToDto(Team team) {
        TeamReadDto teamReadDto = new TeamReadDto();
        teamReadDto.setId(team.getId());
        teamReadDto.setTeamName(team.getTeamName());
        teamReadDto.setSportType(team.getSportType());
        teamReadDto.setFoundationDate(team.getFoundationDate());
        return teamReadDto;
    }

    @Transactional
    @Override
    public List<TeamPlayersDto> readTeam(Long id, String role) {
        if (role == null || role.isEmpty()) {
            return playerRepo.findAllByTeamId(id)
                    .stream()
                    .map(this::convertPlayerToDto)
                    .collect(Collectors.toList());
        } else {
            return playerRepo.findAllByTeamIdAndRoleInTeam(id, role)
                    .stream()
                    .map(this::convertPlayerToDto)
                    .collect(Collectors.toList());
        }

    }

    private TeamPlayersDto convertPlayerToDto(Player player) {
        TeamPlayersDto teamPlayersDto = new TeamPlayersDto();
        teamPlayersDto.setName(player.getName());
        teamPlayersDto.setSurname(player.getSurname());
        teamPlayersDto.setPatronymic(player.getPatronymic());
        teamPlayersDto.setRoleInTeam(player.getRoleInTeam());
        teamPlayersDto.setBirthdate(player.getBirthdate());
        return teamPlayersDto;
    }

    @Transactional
    @Override
    public void createTeam(TeamDto teamDto) {
        teamRepo.save(dtoToTeam(teamDto));
    }

    private Team dtoToTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setTeamName(teamDto.getTeamName());
        team.setSportType(teamDto.getSportType());
        team.setFoundationDate(teamDto.getFoundationDate());
        return team;
    }

    @Transactional
    @Override
    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, TeamUpdDto teamUpdDto) {
        Team team = teamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        team.setTeamName(teamUpdDto.getTeamName() == null ? team.getTeamName() : teamUpdDto.getTeamName());
        team.setSportType(teamUpdDto.getSportType() == null ? team.getSportType() : teamUpdDto.getSportType());
        team.setFoundationDate(teamUpdDto.getFoundationDate() == null ? team.getFoundationDate() : teamUpdDto.getFoundationDate());
    }
}

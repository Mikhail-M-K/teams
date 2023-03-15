package org.example.service;

import org.example.model.PlayerTeam;
import org.example.model.Team;
import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;
import org.example.repos.PlayerTeamRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeamServiceImpl implements TeamService{
    PlayerTeamRepo playerTeamRepo;
    TeamRepo teamRepo;

    public TeamServiceImpl(PlayerTeamRepo playerTeamRepo, TeamRepo teamRepo) {
        this.playerTeamRepo = playerTeamRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public List<TeamDto> readAll(String typeSport, Date startPeriod, Date finishPeriod) {
        System.out.println(typeSport + " " + startPeriod + " " + finishPeriod);
        if (startPeriod == null && finishPeriod == null && (typeSport == null || typeSport.isEmpty())){
            return teamRepo.findAll().stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());
        } else if (startPeriod == null && finishPeriod == null) {
            return teamRepo.findAllBySportType(typeSport)
                    .stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());
        } else {
            //поправить дату
            if (finishPeriod == null) {
                finishPeriod = new Date();
            }
            if (startPeriod == null) {
                startPeriod = new Date("01.01.1000");
            }
            return teamRepo.findAllBySportTypeAndFoundationDateBetween(typeSport, startPeriod, finishPeriod)
                    .stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());
        }
    }

    private TeamDto convertTeamToDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName(team.getTeamName());
        teamDto.setSportType(team.getSportType());
        teamDto.setFoundationDate(team.getFoundationDate());
        return teamDto;
    }

    @Override
    public List<PlayerTeamDto> readTeam(Long id, String role) {
        if (role == null || role.isEmpty()) {
            return playerTeamRepo.findAllByTeam_Id(id).stream()
                    .map(this::convertPlayerToDto)
                    .collect(Collectors.toList());
        } else {
            return playerTeamRepo.findAllByTeam_IdAndRoleInTeam(id, role)
                    .stream()
                    .map(this::convertPlayerToDto)
                    .collect(Collectors.toList());
        }

    }

    private PlayerTeamDto convertPlayerToDto(PlayerTeam playerTeam) {
        PlayerTeamDto playerTeamDto = new PlayerTeamDto();
        playerTeamDto.setName(playerTeam.getName());
        playerTeamDto.setSurname(playerTeam.getSurname());
        playerTeamDto.setPatronymic(playerTeam.getPatronymic());
        playerTeamDto.setRoleInTeam(playerTeam.getRoleInTeam());
        playerTeamDto.setBirthdate(playerTeam.getBirthdate());
        return playerTeamDto;
    }


    @Override
    public void createTeam(TeamDto teamDto) {
        teamRepo.save(dtoToTeam(teamDto));
    }

    private Team dtoToTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setTeamName(teamDto.getTeamName());
        team.setSportType(team.getSportType());
        team.setFoundationDate(teamDto.getFoundationDate());
        return  team;
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }

    @Override
    public void updateTeam(TeamUpdDto teamUpdDto, Long id) {
        Team team = new Team();
        team.setTeamName(teamUpdDto.getTeamName()==null?team.getTeamName():teamUpdDto.getTeamName());
        team.setSportType(teamUpdDto.getSportType()==null?team.getSportType():teamUpdDto.getSportType());
        team.setFoundationDate(teamUpdDto.getFoundationDate()==null?team.getFoundationDate():teamUpdDto.getFoundationDate());
    }
}

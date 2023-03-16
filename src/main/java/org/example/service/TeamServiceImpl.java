package org.example.service;

import org.example.model.PlayerTeam;
import org.example.model.Team;
import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.TeamDto;
import org.example.model.dto.TeamUpdDto;
import org.example.repos.PlayerTeamRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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

    @Transactional
    @Override
    public List<TeamDto> readAll(String typeSport, Date startPeriod, Date finishPeriod) throws ParseException {
        System.out.println(typeSport + " " + startPeriod + " " + finishPeriod);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
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
            if (finishPeriod == null) {
                finishPeriod = ft.parse(String.valueOf(LocalDate.now()));
                System.out.println(finishPeriod);
            }
            if (startPeriod == null) {
                startPeriod = ft.parse("1000-01-01");
                System.out.println(startPeriod);
            }
            if((typeSport == null || typeSport.isEmpty()) && (startPeriod != null || finishPeriod != null)) {
                //поправить дату
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

    private TeamDto convertTeamToDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName(team.getTeamName());
        teamDto.setSportType(team.getSportType());
        teamDto.setFoundationDate(team.getFoundationDate());
        return teamDto;
    }

    @Transactional
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
    public void update( Long id, TeamUpdDto teamUpdDto) {
        Team team = teamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        team.setTeamName(teamUpdDto.getTeamName()==null?team.getTeamName():teamUpdDto.getTeamName());
        team.setSportType(teamUpdDto.getSportType()==null?team.getSportType():teamUpdDto.getSportType());
        team.setFoundationDate(teamUpdDto.getFoundationDate()==null?team.getFoundationDate():teamUpdDto.getFoundationDate());
    }
}

package org.example.service;

import org.example.model.PlayerTeam;
import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;
import org.example.repos.PlayerTeamRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class PlayerTeamServiceImpl implements PlayerTeamService{

    PlayerTeamRepo playerTeamRepo;
    TeamRepo teamRepo;

    public PlayerTeamServiceImpl(PlayerTeamRepo playerTeamRepo, TeamRepo teamRepo) {
        this.playerTeamRepo = playerTeamRepo;
        this.teamRepo = teamRepo;
    }

    @Transactional
    @Override
    public void createPLayer(PlayerTeamDto playerTeamDto) {
        playerTeamRepo.save(dtoToPlayer(playerTeamDto));
    }

    private PlayerTeam dtoToPlayer(PlayerTeamDto playerTeamDto) {
        PlayerTeam player = new PlayerTeam();
        player.setTeam(teamRepo.findById(playerTeamDto.getTeamId()).get());
        player.setName(playerTeamDto.getName());
        player.setSurname(playerTeamDto.getSurname());
        player.setPatronymic(playerTeamDto.getPatronymic());
        player.setBirthdate(playerTeamDto.getBirthdate());
        player.setRoleInTeam(player.getRoleInTeam());
        return player;
    }

    @Transactional
    @Override
    public void transferPlayer(Long id, Long idTeam) {
        PlayerTeam playerTeam = playerTeamRepo.getReferenceById(id);
        playerTeam.setTeam(teamRepo.findById(idTeam).get());
        playerTeamRepo.save(playerTeam);

    }

    @Transactional
    @Override
    public void deletePlayer(Long id) {
        playerTeamRepo.deleteById(id);

    }

    @Transactional
    @Override
    public void update(Long id, PlayerTeamUpdDto playerTeamUpdDto) {
        PlayerTeam playerTeam = playerTeamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        playerTeam.setName(playerTeamUpdDto.getName()==null?playerTeam.getName():playerTeamUpdDto.getName());
        playerTeam.setSurname(playerTeamUpdDto.getSurname()==null?playerTeam.getSurname():playerTeamUpdDto.getSurname());
        playerTeam.setPatronymic(playerTeamUpdDto.getPatronymic()==null?playerTeam.getPatronymic():playerTeamUpdDto.getPatronymic());
        playerTeam.setBirthdate(playerTeamUpdDto.getBirthdate()==null?playerTeam.getBirthdate():playerTeamUpdDto.getBirthdate());
        playerTeam.setRoleInTeam(playerTeamUpdDto.getRoleInTeam()==null?playerTeam.getRoleInTeam():playerTeamUpdDto.getRoleInTeam());
    }
}

package org.example.service;

import org.example.model.PlayerTeam;
import org.example.model.dto.PlayerTeamDto;
import org.example.model.dto.PlayerTeamUpdDto;
import org.example.repos.PlayerTeamRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

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
        playerTeamRepo.save(DtoToPlayer(playerTeamDto));
    }

    private PlayerTeam DtoToPlayer(PlayerTeamDto playerTeamDto) {
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
    public void updatePlayer(PlayerTeamUpdDto playerTeamUpdDto, Long id) {

    }
}

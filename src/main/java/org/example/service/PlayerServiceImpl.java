package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exceptions.IllegalArgumentException;
import org.example.model.Player;
import org.example.model.dto.PlayerDto;
import org.example.model.dto.PlayerUpdDto;
import org.example.repos.PlayerRepo;
import org.example.repos.TeamRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    PlayerRepo playerTeamRepo;
    TeamRepo teamRepo;

    @Transactional
    @Override
    public void createPLayer(PlayerDto playerDto) {
        playerTeamRepo.save(dtoToPlayer(playerDto));
    }

    private Player dtoToPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setTeam(teamRepo.findById(playerDto.getTeamId()).orElseThrow(EntityNotFoundException::new));
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPatronymic(playerDto.getPatronymic());
        player.setBirthdate(playerDto.getBirthdate());
        player.setRoleInTeam(playerDto.getRoleInTeam());
        return player;
    }

    @Transactional
    @Override
    public void transferPlayer(Long id, Long idTeam) {
        Player player = playerTeamRepo.getReferenceById(id);
        if (Objects.equals(player.getTeam().getId(), idTeam)) {
            throw new IllegalArgumentException("Игрок уже играет в этой команде");
        }
        player.setTeam(teamRepo.findById(idTeam).orElseThrow(EntityNotFoundException::new));
        playerTeamRepo.save(player);

    }

    @Transactional
    @Override
    public void deletePlayer(Long id) {
        playerTeamRepo.deleteById(id);

    }

    @Transactional
    @Override
    public void update(Long id, PlayerUpdDto playerUpdDto) {
        Player player = playerTeamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        player.setName(playerUpdDto.getName() == null ? player.getName() : playerUpdDto.getName());
        player.setSurname(playerUpdDto.getSurname() == null ? player.getSurname() : playerUpdDto.getSurname());
        player.setPatronymic(playerUpdDto.getPatronymic() == null ? player.getPatronymic() : playerUpdDto.getPatronymic());
        player.setBirthdate(playerUpdDto.getBirthdate() == null ? player.getBirthdate() : playerUpdDto.getBirthdate());
        player.setRoleInTeam(playerUpdDto.getRoleInTeam() == null ? player.getRoleInTeam() : playerUpdDto.getRoleInTeam());
    }
}

package org.example.repos;

import org.example.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

    List<Player> findAllByTeamIdAndRoleInTeam(Long id, String roleInTeam);

    List<Player> findAllByTeamId(Long id);
}

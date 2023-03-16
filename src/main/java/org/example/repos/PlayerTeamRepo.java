package org.example.repos;

import org.example.model.PlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerTeamRepo extends JpaRepository<PlayerTeam, Long> {

    List<PlayerTeam> findAllByTeam_IdAndRoleInTeam(Long id, String roleInTeam);

    List<PlayerTeam> findAllByTeam_Id(Long id);
}

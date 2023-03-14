package org.example.repos;

import org.example.model.PlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepo extends JpaRepository<PlayerTeam, Long> {
}

package org.example.repos;

import org.example.model.PlayerTeam;
import org.example.model.Team;
import org.example.model.dto.PlayerTeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

}

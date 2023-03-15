package org.example.repos;

import org.example.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    List<Team> findAllBySportTypeAndFoundationDateBetween(String sportType, Date foundationDateBetweenStart, Date foundationDateBetweenFinish);

    List<Team> findAllBySportType(String sportType);

    List<Team> findAllByFoundationDateBetween(Date foundationDateBetweenStart, Date foundationDateBetweenFinish);

}

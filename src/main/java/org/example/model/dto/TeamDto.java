package org.example.model.dto;

import org.example.model.PlayerTeam;

import java.util.Date;
import java.util.Set;

public class TeamDto {

    private Long id;

    private String teamName;

    private String sportType;

    private Date foundationDate;

    private Set<PlayerTeam> playerTeam;

    public TeamDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        sportType = sportType;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Set<PlayerTeam> getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(Set<PlayerTeam> playerTeam) {
        this.playerTeam = playerTeam;
    }
}

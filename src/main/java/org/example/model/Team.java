package org.example.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "teamsIdSeq", sequenceName = "SEQ_SERVICE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamsIdSeq")
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "sport_type")
    private String SportType;

    @Column(name = "foundation_date")
    private Date foundationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private Set<PlayerTeam> playerTeam;

    public Team() {
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
        return SportType;
    }

    public void setSportType(String sportType) {
        SportType = sportType;
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

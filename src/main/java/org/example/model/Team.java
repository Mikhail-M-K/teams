package org.example.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "teamsIdSeq", sequenceName = "teams_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "teamsIdSeq")
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


}

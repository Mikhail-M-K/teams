package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "teamsIdSeq", sequenceName = "SEQ_SERVICE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamsIdSeq")
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "sport_type")
    private String sportType;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade = CascadeType.REMOVE)
    private Set<Player> playerTeam;
}

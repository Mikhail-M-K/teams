package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="players_team")
public class PlayerTeam {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="players_teamIdSeq", sequenceName="players_team_id_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "players_teamIdSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id", nullable = false)
    private Team team;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="patronymic")
    private String patronymic;

    @Column(name="birthdate")
    private Date birthdate;

    @Column(name="role_in_team")
    private String roleInTeam;
}

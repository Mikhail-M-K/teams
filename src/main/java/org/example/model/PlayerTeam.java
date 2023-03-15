package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="players_team")
public class PlayerTeam {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="players_teamIdSeq", sequenceName="SEQ_SERVICE", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "players_teamIdSeq")
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
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name="role_in_team")
    private String roleInTeam;

    public PlayerTeam() {
    }

    public PlayerTeam(Team team, String name, String surname, String patronymic, Date birthdate, String roleInTeam) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.roleInTeam = roleInTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getRoleInTeam() {
        return roleInTeam;
    }

    public void setRoleInTeam(String roleInTeam) {
        this.roleInTeam = roleInTeam;
    }
}

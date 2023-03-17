package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TeamPlayersDto {
    private String name;

    private String surname;

    private String patronymic;

    private Date birthdate;

    private String roleInTeam;
}

package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {

    private Long id;

    private Long teamId;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthdate;

    private String roleInTeam;
}

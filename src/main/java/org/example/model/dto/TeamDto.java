package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private Long id;

    private String teamName;

    private String sportType;

    private LocalDate foundationDate;


}

package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.PlayerTeam;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private Long id;

    private String teamName;

    private String sportType;

    private Date foundationDate;

    private Set<PlayerTeam> playerTeam;

}

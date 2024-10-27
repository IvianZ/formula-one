package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StandingTeamDto {
    private String teamName;
    private Integer totalPoints;
}

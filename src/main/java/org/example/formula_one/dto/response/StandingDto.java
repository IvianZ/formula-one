package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StandingDto {
    private String driverName;
    private String driverTeam;
    private Integer totalPoints;
}

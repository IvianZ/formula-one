package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FullDriverDto {

    private int id;
    private String firstName;
    private String lastName;
    private BasicTeamDto team;

}

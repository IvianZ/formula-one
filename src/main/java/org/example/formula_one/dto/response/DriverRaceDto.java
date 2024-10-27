package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DriverRaceDto {

    private FullDriverDto driver;
    private LocalTime finishedFor;
    private int points;

}

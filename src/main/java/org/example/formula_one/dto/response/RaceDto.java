package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.formula_one.model.DriverRace;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RaceDto {

    private int id;
    private String name;

    @Builder.Default
    private List<DriverRaceDto> driverTimes = new ArrayList<>();

}

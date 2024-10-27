package org.example.formula_one.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.formula_one.model.Driver;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDto {

    private int id;
    private String name;

    @Builder.Default
    private List<DriverDto> driverList = new ArrayList<>();


}

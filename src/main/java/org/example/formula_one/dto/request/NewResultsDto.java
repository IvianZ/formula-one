package org.example.formula_one.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewResultsDto {

    private int raceId;
    private List<NewResultsTimeDto> times;
}

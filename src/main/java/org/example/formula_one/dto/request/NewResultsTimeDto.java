package org.example.formula_one.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewResultsTimeDto {
    private Integer driverId;
    private LocalTime time;
}

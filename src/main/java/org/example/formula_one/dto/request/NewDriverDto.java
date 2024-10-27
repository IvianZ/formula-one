package org.example.formula_one.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDriverDto {
    private String firstName;
    private String lastName;
    private Integer teamId;

}

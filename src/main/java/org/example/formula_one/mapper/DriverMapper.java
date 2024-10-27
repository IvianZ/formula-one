package org.example.formula_one.mapper;

import org.example.formula_one.dto.response.FullDriverDto;
import org.example.formula_one.dto.response.TeamDto;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    FullDriverDto toFullDriverDto(Driver driver);

}

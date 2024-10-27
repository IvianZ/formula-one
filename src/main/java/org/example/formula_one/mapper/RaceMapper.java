package org.example.formula_one.mapper;

import org.example.formula_one.dto.response.FullDriverDto;
import org.example.formula_one.dto.response.RaceDto;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    @Mapping(target = "driverTimes", source = "driverRaceList")
    RaceDto toRaceDto(Race race);

}

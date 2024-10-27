package org.example.formula_one.mapper;

import org.example.formula_one.dto.response.StandingDto;
import org.example.formula_one.dto.response.StandingTeamDto;
import org.example.formula_one.dto.response.TeamDto;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StandingMapper {

    TeamDto toTeamDto(Team team);

    @Mapping(target = "totalPoints", source = "points")
    @Mapping(target = "driverTeam", source = "driver.team.name")
    @Mapping(target = "driverName", source = "driver", qualifiedByName = "customDriverName")
    StandingDto toStandingDto(Driver driver, Integer points);

    @Mapping(target = "totalPoints", source = "points")
    @Mapping(target = "teamName", source = "team.name")
    StandingTeamDto toStandingTeamDto(Team team, Integer points);

    @Named("customDriverName")
    default String customDriverName(Driver driver) {
        return String.format("%s %s", driver.getFirstName(), driver.getLastName());
    }

}

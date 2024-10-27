package org.example.formula_one.mapper;

import org.example.formula_one.dto.response.TeamDto;
import org.example.formula_one.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto toTeamDto(Team team);

}

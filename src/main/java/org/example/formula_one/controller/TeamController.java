package org.example.formula_one.controller;

import lombok.RequiredArgsConstructor;
import org.example.formula_one.dto.request.NewTeamDto;
import org.example.formula_one.dto.response.TeamDto;
import org.example.formula_one.exception.TeamNotFoundException;
import org.example.formula_one.mapper.TeamMapper;
import org.example.formula_one.model.Team;
import org.example.formula_one.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/team")
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping
    public List<TeamDto> getTeams() {
        return teamService.getAll().stream()
                .map(teamMapper::toTeamDto)
                .toList();
    }

    @GetMapping("/{teamId}")
    public TeamDto getSingleTeam(@PathVariable int teamId ) {
        return teamService.getById(teamId)
                .map(teamMapper::toTeamDto)
                .orElseThrow(TeamNotFoundException::new);
    }

    @PostMapping
    public TeamDto newTeam(@RequestBody NewTeamDto request){
        Team team = Team.builder()
                .name(request.getName())
                .build();

        return teamMapper.toTeamDto(teamService.save(team));
    }

}

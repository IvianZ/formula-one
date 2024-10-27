package org.example.formula_one.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.formula_one.dto.request.EnrollDriverRaceDto;
import org.example.formula_one.dto.request.NewRaceDto;
import org.example.formula_one.dto.request.NewResultsDto;
import org.example.formula_one.dto.response.RaceDto;
import org.example.formula_one.dto.response.StandingDto;
import org.example.formula_one.dto.response.StandingTeamDto;
import org.example.formula_one.exception.DriverNotFoundException;
import org.example.formula_one.exception.RaceNotFoundException;
import org.example.formula_one.mapper.RaceMapper;
import org.example.formula_one.mapper.StandingMapper;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Race;
import org.example.formula_one.model.Team;
import org.example.formula_one.service.DriverRaceService;
import org.example.formula_one.service.DriverService;
import org.example.formula_one.service.RaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("api/v1/race")
public class RaceController {

    private final RaceService raceService;
    private final DriverService driverService;
    private final DriverRaceService driverRaceService;
    private final RaceMapper raceMapper;
    private final StandingMapper standingMapper;

    @PostMapping
    public RaceDto newRace(@RequestBody NewRaceDto request) {
        Race race = Race.builder()
                .name(request.getName())
                .build();

        return raceMapper.toRaceDto(raceService.save(race));
    }

    @PostMapping("/enrol/{driverId}")
    public RaceDto enrollDriver(@PathVariable Integer driverId, @RequestBody EnrollDriverRaceDto request) {
        Driver driver = driverService.getById(driverId).orElseThrow(DriverNotFoundException::new);
        Race race = raceService.getById(request.getRaceId()).orElseThrow(RaceNotFoundException::new);

        return raceMapper.toRaceDto(driverRaceService.enrollDriverToRace(driver, race));
    }

    @PostMapping("/result")
    public RaceDto postResult(@RequestBody NewResultsDto request) {
        Race race = raceService.getById(request.getRaceId()).orElseThrow(RaceNotFoundException::new);

        return raceMapper.toRaceDto(driverRaceService.postResult(race, request.getTimes()));
    }

    @GetMapping("/standing")
    public List<StandingDto> globalRanking() {
        return driverService.getSortedDriversByPoints()
                .map(entry -> standingMapper.toStandingDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    @GetMapping("/standing/teams")
    public List<StandingTeamDto> globalRankingByTeam() {
        return driverService.getSortedTeamsByPoints()
                .map(entry -> standingMapper.toStandingTeamDto(entry.getKey(), entry.getValue()))
                .toList();
    }

}

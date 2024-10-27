package org.example.formula_one.controller;

import lombok.RequiredArgsConstructor;
import org.example.formula_one.dto.request.NewDriverDto;
import org.example.formula_one.dto.response.FullDriverDto;
import org.example.formula_one.exception.DriverNotFoundException;
import org.example.formula_one.exception.TeamMaxDriversReachedException;
import org.example.formula_one.exception.TeamNotFoundException;
import org.example.formula_one.mapper.DriverMapper;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Team;
import org.example.formula_one.service.DriverService;
import org.example.formula_one.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/driver")
public class DriverController {

    private final DriverService driverService;
    private final TeamService teamService;
    private final DriverMapper driverMapper;

    @GetMapping
    public List<FullDriverDto> getDrivers() {
        return driverService.findAll().stream().map(driverMapper::toFullDriverDto).toList();
    }

    @GetMapping("/{driverId}")
    public FullDriverDto getDriverById(@PathVariable int driverId) {
        return driverService.getById(driverId)
                .map(driverMapper::toFullDriverDto)
                .orElseThrow(DriverNotFoundException::new);
    }

    @PostMapping
    public FullDriverDto newDriver(@RequestBody NewDriverDto request) {
        Team team = teamService.getById(request.getTeamId()).orElseThrow(TeamNotFoundException::new);

        if (team.getDriverList().size() >= 4) {
            throw new TeamMaxDriversReachedException();
        }

        Driver driver = Driver.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .team(team)
                .build();

        return driverMapper.toFullDriverDto(driverService.save(driver));
    }

    @PutMapping("/{driverId}/transfer-to/{teamId}")
    public FullDriverDto transferDriverToTeam(@PathVariable int driverId, @PathVariable int teamId) {
        Driver driver = driverService.getById(driverId).orElseThrow(DriverNotFoundException::new);
        Team team = teamService.getById(teamId).orElseThrow(TeamNotFoundException::new);

        if (team.getDriverList().size() >= 4) {
            throw new TeamMaxDriversReachedException();
        }

        driver.setTeam(team);

        return driverMapper.toFullDriverDto(driverService.save(driver));
    }

    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable int driverId) {
        Driver driver = driverService.getById(driverId).orElseThrow(DriverNotFoundException::new);

        driverService.deleteDrive(driver);
    }

}

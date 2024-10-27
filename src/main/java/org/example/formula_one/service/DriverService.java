package org.example.formula_one.service;

import lombok.RequiredArgsConstructor;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.Team;
import org.example.formula_one.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getById(int id) {
        return driverRepository.findById(id);
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDrive(Driver driver) {
        driverRepository.delete(driver);
    }

    public Stream<Map.Entry<Driver, Integer>> getSortedDriversByPoints() {
        Map<Driver, Integer> pointsByDriver = new HashMap<>();

        driverRepository.findAll().forEach(driver -> {
            driver.getDriverRaceList().forEach(driverRace -> {
                pointsByDriver.put(
                        driver,
                        pointsByDriver.getOrDefault(driver, 0) + driverRace.getPoints()
                );
            });
        });

        return pointsByDriver.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
    }

    public Stream<Map.Entry<Team, Integer>> getSortedTeamsByPoints() {
        Map<Team, Integer> pointsByTeam = new HashMap<>();

        getSortedDriversByPoints().forEach(entry -> {
           pointsByTeam.put(
                   entry.getKey().getTeam(),
                   pointsByTeam.getOrDefault(entry.getKey().getTeam(), 0) + entry.getValue()
           );
        });

        return pointsByTeam.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
    }

}

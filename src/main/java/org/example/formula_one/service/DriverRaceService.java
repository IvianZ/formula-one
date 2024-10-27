package org.example.formula_one.service;

import lombok.RequiredArgsConstructor;
import org.example.formula_one.dto.request.NewResultsTimeDto;
import org.example.formula_one.exception.RaceNotFoundException;
import org.example.formula_one.model.Driver;
import org.example.formula_one.model.DriverRace;
import org.example.formula_one.model.Race;
import org.example.formula_one.repository.DriverRaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class DriverRaceService {

    private static final Integer INITIAL_POINTS = 20;

    private final DriverRaceRepository driverRaceRepository;
    private final RaceService raceService;

    public Race enrollDriverToRace(Driver driver, Race race) {
        DriverRace driverRace = DriverRace.builder()
                .driver(driver)
                .race(race)
                .build();

        driverRaceRepository.save(driverRace);

        return raceService.getById(race.getId()).orElseThrow(RaceNotFoundException::new);
    }

    public Race postResult(Race race, List<NewResultsTimeDto> times) {
        AtomicReference<Integer> initialPoints = new AtomicReference<>(INITIAL_POINTS);

        times.stream()
                .sorted((time1, time2) -> time1.getTime().compareTo(time2.getTime()))
                .forEach(time -> {

                    race.getDriverRaceList()
                            .stream()
                            .filter(driverRace -> driverRace.getDriver().getId().equals(time.getDriverId()))
                            .forEach(driverRace -> {
                                initialPoints.updateAndGet(value -> (value <= 0 ? 0 : value - 1));

                                driverRace.setFinishedFor(time.getTime());

                                driverRace.setPoints(initialPoints.get());
                            });
                });

        return raceService.save(race);
    }

}

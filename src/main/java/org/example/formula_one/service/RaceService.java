package org.example.formula_one.service;

import lombok.RequiredArgsConstructor;
import org.example.formula_one.model.Race;
import org.example.formula_one.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    public Race save(Race race) {
        return raceRepository.save(race);
    }
    public Optional<Race> getById(int id) {
        return raceRepository.findById(id);
    }

}

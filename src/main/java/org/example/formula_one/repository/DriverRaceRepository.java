package org.example.formula_one.repository;

import org.example.formula_one.model.DriverRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRaceRepository extends JpaRepository<DriverRace, Integer> {


}

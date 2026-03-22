package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.Ipl2026Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Ipl2026MatchRepository extends JpaRepository<Ipl2026Match, Integer> {

    Optional<Ipl2026Match> findByMatchNumber(Integer matchNumber);
}

package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.Ipl2026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Ipl2026MatchRepository extends JpaRepository<Ipl2026Match, Integer> {

    Optional<Ipl2026Match> findByMatchNumber(Integer matchNumber);
}

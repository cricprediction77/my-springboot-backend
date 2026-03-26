package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.Psl2026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Psl2026MatchRepository extends JpaRepository<Psl2026Match, Integer> {

    Optional<Psl2026Match> findByMatchNumber(Integer matchNumber);
}

package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.SaT202026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaT202026MatchRepository
        extends JpaRepository<SaT202026Match, Integer> {

    Optional<SaT202026Match> findByMatchNumber(Integer matchNumber);
}

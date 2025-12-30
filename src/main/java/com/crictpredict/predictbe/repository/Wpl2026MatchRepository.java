package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.Wpl2026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Wpl2026MatchRepository extends JpaRepository<Wpl2026Match, Integer> {
    // No extra methods needed for now
}

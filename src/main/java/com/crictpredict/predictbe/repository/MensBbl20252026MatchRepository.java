package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.MensBbl20252026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MensBbl20252026MatchRepository
        extends JpaRepository<MensBbl20252026Match, Integer> {

    Optional<MensBbl20252026Match> findByMatchNumber(Integer matchNumber);
}

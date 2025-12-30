package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.Bpl20252026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Bpl20252026MatchRepository extends JpaRepository<Bpl20252026Match, Integer> {
    Optional<Bpl20252026Match> findByMatchNumber(Integer matchNumber);

}
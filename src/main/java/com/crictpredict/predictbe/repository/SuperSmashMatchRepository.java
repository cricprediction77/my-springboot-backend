package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.SuperSmash20252026Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperSmashMatchRepository extends JpaRepository<SuperSmash20252026Match, Integer> {
    Optional<SuperSmash20252026Match> findByMatchNumber(Integer matchNumber);
}

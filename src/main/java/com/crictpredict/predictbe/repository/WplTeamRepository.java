package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.WplTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WplTeamRepository extends JpaRepository<WplTeam, Long> {
}

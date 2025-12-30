package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.BplTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BplTeamRepository extends JpaRepository<BplTeam, Long> {

    BplTeam findByTeamName(String teamName);

    BplTeam findByShortName(String shortName);
}

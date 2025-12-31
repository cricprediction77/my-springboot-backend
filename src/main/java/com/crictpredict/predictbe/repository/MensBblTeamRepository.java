package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.MensBblTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensBblTeamRepository
        extends JpaRepository<MensBblTeam, Long> {
}

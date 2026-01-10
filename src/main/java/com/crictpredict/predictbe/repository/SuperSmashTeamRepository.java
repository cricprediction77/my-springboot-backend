package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.SuperSmashTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperSmashTeamRepository extends JpaRepository<SuperSmashTeam, Long> {
}

package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.SaT20Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaT20TeamRepository
        extends JpaRepository<SaT20Team, Long> {
}

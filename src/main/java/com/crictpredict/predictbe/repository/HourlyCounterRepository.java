package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.HourlyCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyCounterRepository extends JpaRepository<HourlyCounter, Long> {
}
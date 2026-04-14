package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.entity.HourlyCounter;
import com.crictpredict.predictbe.repository.HourlyCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class CounterService {

    @Autowired
    private HourlyCounterRepository repository;

    // Get existing or create new
    public HourlyCounter getCounter() {
        return repository.findAll().stream().findFirst()
                .orElseGet(this::createInitialRecord);
    }

    private HourlyCounter createInitialRecord() {
        HourlyCounter counter = new HourlyCounter();
        counter.setLastGeneratedCount(0);
        counter.setTodayCount(0);
        counter.setTotalCount(61430); // initial value
        counter.setLastUpdatedDate(LocalDate.now());
        return repository.save(counter);
    }

    // Main logic (hourly update)
    public void updateCounter() {

        HourlyCounter counter = getCounter();

        // Reset if new day
        if (!counter.getLastUpdatedDate().equals(LocalDate.now())) {
            counter.setTodayCount(0);
            counter.setLastUpdatedDate(LocalDate.now());
        }

        int random = new Random().nextInt(101); // 0–31

        counter.setLastGeneratedCount(random);
        counter.setTodayCount(counter.getTodayCount() + random);
        counter.setTotalCount(counter.getTotalCount() + random);

        repository.save(counter);
    }
    public void resetTodayCount() {
        HourlyCounter counter = getCounter();
        counter.setTodayCount(0);
        counter.setLastUpdatedDate(LocalDate.now());
        repository.save(counter);
    }
}
package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.entity.HourlyCounter;
import com.crictpredict.predictbe.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @GetMapping
    public HourlyCounter getCounter() {
        return counterService.getCounter();
    }
}
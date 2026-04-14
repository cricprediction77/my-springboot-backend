package com.crictpredict.predictbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PredictBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PredictBeApplication.class, args);
	}

}

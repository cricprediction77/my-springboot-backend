package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "hourly_counter")
@Data
public class HourlyCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_generated_count")
    private Integer lastGeneratedCount;

    @Column(name = "today_count")
    private Integer todayCount;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;
}
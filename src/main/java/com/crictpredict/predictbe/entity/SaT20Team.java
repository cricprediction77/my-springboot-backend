package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sa_t20_teams")
public class SaT20Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;
}

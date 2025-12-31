package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mens_bbl_teams")
public class MensBblTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;
}

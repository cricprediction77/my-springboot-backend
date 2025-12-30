package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bpl_2025_2026")
public class Bpl20252026Match {

    @Id
    @Column(name = "match_number")
    private Integer matchNumber;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "teams", nullable = false)
    private String teams;

    @Column(name = "approx_start_time")
    private String approxStartTime;

    @Column(name = "toss_winner")
    private String tossWinner;

    @Column(name = "match_winner")
    private String matchWinner;

    @Column(name = "league_type", nullable = false)
    private String leagueType;

    @Column(name = "team1_score")
    private String team1Score;

    @Column(name = "team2_score")
    private String team2Score;

    @Column(name = "session_details", columnDefinition = "TEXT")
    private String sessionDetails;

    @Column(name = "match_status")
    private String matchStatus;
}
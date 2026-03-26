package com.crictpredict.predictbe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "psl_2026_season")
@Data
public class Psl2026Match {

    @Id
    @Column(name = "match_number")
    private Integer matchNumber;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "teams", nullable = false)
    private String teams;

    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "approx_start_time", nullable = false)
    private String approxStartTime; // IST HH:mm

    @Column(name = "league_type", nullable = false)
    private String leagueType; // WPL

    // 🔹 Result Fields
    @Column(name = "toss_winner")
    private String tossWinner;

    @Column(name = "match_winner")
    private String matchWinner;

    @Column(name = "team1_score")
    private String team1Score;

    @Column(name = "team2_score")
    private String team2Score;

    @Column(name = "match_status")
    private String matchStatus; // UPCOMING | LIVE | COMPLETED

    // 🔥 SESSION DETAILS (LONG TEXT)
    @Column(name = "session_details", columnDefinition = "TEXT")
    private String sessionDetails;
}


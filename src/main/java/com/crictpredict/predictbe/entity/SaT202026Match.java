package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sa_t20_2026")
public class SaT202026Match {

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
    private String approxStartTime; // HH:mm (Local Time / IST as stored)

    @Column(name = "league_type", nullable = false)
    private String leagueType; // SA T20 2026

    // 🔹 Result fields
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

    // 🔥 Long text commentary / session info
    @Column(name = "session_details", columnDefinition = "TEXT")
    private String sessionDetails;
}

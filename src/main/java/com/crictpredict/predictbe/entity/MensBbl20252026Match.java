package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "mens_bbl_2025_2026")
public class MensBbl20252026Match {

    @Id
    @Column(name = "match_number")
    private Integer matchNumber;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "teams", nullable = false)
    private String teams;

    @Column(name = "venue")
    private String venue;

    @Column(name = "approx_start_time")
    private String approxStartTime; // Local time HH:mm

    @Column(name = "league_type", nullable = false)
    private String leagueType; // Mens Big Bash League 2025-2026

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

    @Column(name = "session_details", columnDefinition = "TEXT")
    private String sessionDetails;
}

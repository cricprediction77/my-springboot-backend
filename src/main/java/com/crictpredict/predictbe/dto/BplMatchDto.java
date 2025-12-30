package com.crictpredict.predictbe.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BplMatchDto {

    private Integer matchNumber;
    private LocalDate matchDate;
    private String teams;
    private String approxStartTime;

    private String tossWinner;
    private String matchWinner;

    private String leagueType;
    private String matchStatus;

    private String team1Score;
    private String team2Score;

    // ✅ Structured session details
    private List<SessionDetailDto> sessionDetails;
}

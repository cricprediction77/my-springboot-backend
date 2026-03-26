package com.crictpredict.predictbe.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PslMatchDto {
    private Integer matchNumber;
    private LocalDate matchDate;
    private String teams;
    private String venue;
    private String approxStartTime;
    private String leagueType;

    private String tossWinner;
    private String matchWinner;
    private String team1Score;
    private String team2Score;
    private String matchStatus;

    // ✅ IMPORTANT: sessionDetails as LIST (FE expects Array)
    private List<SessionDetailDto> sessionDetails;
}

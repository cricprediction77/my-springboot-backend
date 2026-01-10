package com.crictpredict.predictbe.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SuperSmashMatchDto {

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

    // ✅ IMPORTANT: FE expects array
    private List<SessionDetailDto> sessionDetails;
}

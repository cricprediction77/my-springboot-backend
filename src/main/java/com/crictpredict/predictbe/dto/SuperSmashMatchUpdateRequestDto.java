package com.crictpredict.predictbe.dto;

import lombok.Data;

@Data
public class SuperSmashMatchUpdateRequestDto {

    private Integer matchNumber;

    private String tossWinner;
    private String matchWinner;

    private String team1Score;
    private String team2Score;

    private String sessionDetails;

    private String matchStatus; // COMPLETED
}

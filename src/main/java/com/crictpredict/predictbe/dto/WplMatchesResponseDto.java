package com.crictpredict.predictbe.dto;

import com.crictpredict.predictbe.entity.Wpl2026Match;
import com.crictpredict.predictbe.entity.WplTeam;
import lombok.Data;

import java.util.List;

@Data
public class WplMatchesResponseDto {

    private List<Wpl2026Match> matches;
    private List<WplTeam> teams;
}

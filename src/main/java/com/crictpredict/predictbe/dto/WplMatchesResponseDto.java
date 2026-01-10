package com.crictpredict.predictbe.dto;

import com.crictpredict.predictbe.entity.WplTeam;
import lombok.Data;

import java.util.List;

@Data
public class WplMatchesResponseDto {

    private List<WplMatchDto> matches;   // ✅ changed
    private List<WplTeam> teams;
}

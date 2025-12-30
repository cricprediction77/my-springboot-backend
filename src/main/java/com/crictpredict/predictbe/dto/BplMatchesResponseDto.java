package com.crictpredict.predictbe.dto;

import com.crictpredict.predictbe.entity.BplTeam;
import lombok.Data;

import java.util.List;

@Data
public class BplMatchesResponseDto {

    private List<BplMatchDto> matches;
    private List<BplTeam> teams;
}

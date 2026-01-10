package com.crictpredict.predictbe.dto;

import com.crictpredict.predictbe.entity.SuperSmashTeam;
import lombok.Data;

import java.util.List;

@Data
public class SuperSmashMatchesResponseDto {

    private List<SuperSmashMatchDto> matches;   // ✅ DTO not Entity
    private List<SuperSmashTeam> teams;
}

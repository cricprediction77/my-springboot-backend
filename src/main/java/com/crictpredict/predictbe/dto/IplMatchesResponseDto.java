package com.crictpredict.predictbe.dto;

import lombok.Data;

import java.util.List;

@Data
public class IplMatchesResponseDto {

    private List<IplMatchDto> matches;
}

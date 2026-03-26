package com.crictpredict.predictbe.dto;

import lombok.Data;

import java.util.List;

@Data
public class PslMatchesResponseDto {

    private List<PslMatchDto> matches;

}

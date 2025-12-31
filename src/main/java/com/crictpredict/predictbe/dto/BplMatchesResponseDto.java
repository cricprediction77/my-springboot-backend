package com.crictpredict.predictbe.dto;

import lombok.Data;
import java.util.List;

@Data
public class BplMatchesResponseDto<T> {

    private List<BplMatchDto> matches;
    private List<T> teams;
}

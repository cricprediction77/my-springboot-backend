package com.crictpredict.predictbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDetailDto {
    private String sessionText;
    private String updatedAt;
}

package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.WplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.WplMatchesResponseDto;
import com.crictpredict.predictbe.service.WplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wpl")
public class WplController {

    @Autowired
    private WplService wplService;

    @GetMapping("/wpl-matches")
    public WplMatchesResponseDto getWplMatches() {
        return wplService.getWplMatchesWithTeams();
    }

    @PostMapping("/details")
    public ResponseEntity<String> updateWplMatchDetails(
            @RequestBody WplMatchUpdateRequestDto request
    ) {
        wplService.updateMatchDetails(request);
        return ResponseEntity.ok("WPL match details submitted successfully ✅");
    }
}

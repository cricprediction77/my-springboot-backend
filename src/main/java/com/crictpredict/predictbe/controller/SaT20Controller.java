package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.BplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.BplMatchesResponseDto;
import com.crictpredict.predictbe.entity.SaT20Team;
import com.crictpredict.predictbe.service.SaT20Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sa-t20")
public class SaT20Controller {

    private final SaT20Service service;

    public SaT20Controller(SaT20Service service) {
        this.service = service;
    }

    // ✅ GET ALL MATCHES + TEAMS
    @GetMapping("/sat20-matches")
    public BplMatchesResponseDto<SaT20Team> getSaT20Matches() {
        return service.getSaT20MatchesWithTeams();
    }


    // ✅ POST MATCH RESULT UPDATE
    @PostMapping("/details")
    public String updateSaT20MatchDetails(
            @RequestBody BplMatchUpdateRequestDto request
    ) {
        try {
            service.updateMatchDetails(request);
            return "SA T20 match details submitted successfully";
        } catch (Exception e) {
            return "Failed to submit SA T20 match details: " + e.getMessage();
        }
    }
}

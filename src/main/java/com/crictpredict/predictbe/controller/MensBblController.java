package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.BplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.BplMatchesResponseDto;
import com.crictpredict.predictbe.service.MensBblService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mens-bbl")
public class MensBblController {

    private final MensBblService service;

    public MensBblController(MensBblService service) {
        this.service = service;
    }

    // ✅ GET ALL MATCHES + TEAMS
    @GetMapping("/bbl-matches")
    public BplMatchesResponseDto getMensBblMatchesWithTeams() {
        return service.getMensBblMatchesWithTeams();
    }

    // ✅ UPDATE MATCH DETAILS
    @PostMapping("/details")
    public String updateMensBblMatchDetails(
            @RequestBody BplMatchUpdateRequestDto request
    ) {
        try {
            service.updateMatchDetails(request);
            return "Mens BBL match details submitted successfully";
        } catch (Exception e) {
            return "Failed to submit match details: " + e.getMessage();
        }
    }
}

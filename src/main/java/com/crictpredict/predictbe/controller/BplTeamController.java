package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.BplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.BplMatchesResponseDto;
import com.crictpredict.predictbe.entity.BplTeam;
import com.crictpredict.predictbe.service.BplTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bpl")
public class BplTeamController {

    private final BplTeamService service;

    public BplTeamController(BplTeamService service) {
        this.service = service;
    }

    // ✅ UPDATED MATCHES API
    @GetMapping("/bpl-matches")
    public BplMatchesResponseDto<BplTeam> getBplMatchesWithTeams() {
        return service.getBplMatchesWithTeams();
    }

    @PostMapping("/details")
    public ResponseEntity<String> updateBplMatchDetails(@RequestBody BplMatchUpdateRequestDto request) {
        service.updateMatchDetails(request);
        return ResponseEntity.ok("Match details submitted successfully");
    }

}

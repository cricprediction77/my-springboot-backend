package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.IplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.IplMatchesResponseDto;
import com.crictpredict.predictbe.service.IplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ipl")
public class IplController {

    @Autowired
    private IplService iplService;

    @GetMapping("/ipl-matches")
    public IplMatchesResponseDto getIplMatches() {
        return iplService.getIplMatchesWithTeams();
    }

    @PostMapping("/details")
    public ResponseEntity<String> updateIplMatchDetails(
            @RequestBody IplMatchUpdateRequestDto request
    ) {
        iplService.updateMatchDetails(request);
        return ResponseEntity.ok("IPL match details submitted successfully ✅");
    }
}

package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.PslMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.PslMatchesResponseDto;
import com.crictpredict.predictbe.service.PslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psl")
public class PslController {

    @Autowired
    private PslService pslService;

    @GetMapping("/psl-matches")
    public PslMatchesResponseDto getPslMatches() {
        return pslService.getPslMatchesWithTeams();
    }

    @PostMapping("/details")
    public ResponseEntity<String> updatePslMatchDetails(
            @RequestBody PslMatchUpdateRequestDto request
    ) {
        pslService.updateMatchDetails(request);
        return ResponseEntity.ok("PSL match details submitted successfully ✅");
    }
}

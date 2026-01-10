package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.dto.SuperSmashMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.SuperSmashMatchesResponseDto;
import com.crictpredict.predictbe.service.SuperSmashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/super-smash")
public class SuperSmashController {

    @Autowired
    private SuperSmashService superSmashService;

    @GetMapping("/supersmash-matches")
    public SuperSmashMatchesResponseDto getSuperSmashMatches() {
        return superSmashService.getSuperSmashMatchesWithTeams();
    }

    @PostMapping("/details")
    public ResponseEntity<String> updateSuperSmashMatchDetails(
            @RequestBody SuperSmashMatchUpdateRequestDto request
    ) {
        superSmashService.updateMatchDetails(request);
        return ResponseEntity.ok("Super Smash match details submitted successfully ✅");
    }
}

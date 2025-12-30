package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.WplMatchesResponseDto;
import com.crictpredict.predictbe.entity.Wpl2026Match;
import com.crictpredict.predictbe.entity.WplTeam;
import com.crictpredict.predictbe.repository.Wpl2026MatchRepository;
import com.crictpredict.predictbe.repository.WplTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WplService {

    @Autowired
    private Wpl2026MatchRepository wpl2026MatchRepository;

    @Autowired
    private WplTeamRepository wplTeamRepository;

    public WplMatchesResponseDto getWplMatchesWithTeams() {

        List<Wpl2026Match> matches = wpl2026MatchRepository.findAll();
        List<WplTeam> teams = wplTeamRepository.findAll();

        WplMatchesResponseDto response = new WplMatchesResponseDto();
        response.setMatches(matches);
        response.setTeams(teams);

        return response;
    }
}

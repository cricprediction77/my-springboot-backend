package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.*;
import com.crictpredict.predictbe.entity.Psl2026Match;
import com.crictpredict.predictbe.repository.Psl2026MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PslService {

    @Autowired
    private Psl2026MatchRepository pslRepo;

    public PslMatchesResponseDto getPslMatchesWithTeams() {

        List<Psl2026Match> matches =
                pslRepo.findAll(Sort.by(Sort.Direction.ASC, "matchNumber"));

        List<PslMatchDto> matchDtos = matches.stream()
                .map(this::mapToDto)
                .toList();

        PslMatchesResponseDto response = new PslMatchesResponseDto();
        response.setMatches(matchDtos);

        return response;
    }

    public void updateMatchDetails(PslMatchUpdateRequestDto request) {

        Psl2026Match match = pslRepo
                .findByMatchNumber(request.getMatchNumber())
                .orElseThrow();

        match.setTossWinner(request.getTossWinner());
        match.setMatchWinner(request.getMatchWinner());
        match.setTeam1Score(request.getTeam1Score());
        match.setTeam2Score(request.getTeam2Score());
        match.setSessionDetails(request.getSessionDetails());
        match.setMatchStatus(request.getMatchStatus());

        pslRepo.save(match);
    }

    private PslMatchDto mapToDto(Psl2026Match match) {
        PslMatchDto dto = new PslMatchDto();

        dto.setMatchNumber(match.getMatchNumber());
        dto.setMatchDate(match.getMatchDate());
        dto.setTeams(match.getTeams());
        dto.setVenue(match.getVenue());
        dto.setApproxStartTime(match.getApproxStartTime());
        dto.setLeagueType(match.getLeagueType());

        dto.setTossWinner(match.getTossWinner());
        dto.setMatchWinner(match.getMatchWinner());
        dto.setTeam1Score(match.getTeam1Score());
        dto.setTeam2Score(match.getTeam2Score());
        dto.setMatchStatus(match.getMatchStatus());

        dto.setSessionDetails(null); // same as WPL logic if needed

        return dto;
    }
}

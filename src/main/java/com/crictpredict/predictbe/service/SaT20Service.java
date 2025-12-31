package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.BplMatchDto;
import com.crictpredict.predictbe.dto.BplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.BplMatchesResponseDto;
import com.crictpredict.predictbe.dto.SessionDetailDto;
import com.crictpredict.predictbe.entity.SaT202026Match;
import com.crictpredict.predictbe.entity.SaT20Team;
import com.crictpredict.predictbe.repository.SaT202026MatchRepository;
import com.crictpredict.predictbe.repository.SaT20TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaT20Service {

    @Autowired
    private SaT20TeamRepository teamRepository;

    @Autowired
    private SaT202026MatchRepository matchRepository;

    // ✅ GET ALL MATCHES + TEAMS
    public BplMatchesResponseDto<SaT20Team> getSaT20MatchesWithTeams() {

        List<SaT202026Match> matches = matchRepository.findAll();
        List<SaT20Team> teams = teamRepository.findAll();

        List<BplMatchDto> matchDtos = matches.stream()
                .map(this::mapToMatchDto)
                .toList();

        BplMatchesResponseDto<SaT20Team> response = new BplMatchesResponseDto<>();
        response.setMatches(matchDtos);
        response.setTeams(teams);

        return response;
    }

    // ---------------- HELPERS ----------------

    private BplMatchDto mapToMatchDto(SaT202026Match match) {

        BplMatchDto dto = new BplMatchDto();

        dto.setMatchNumber(match.getMatchNumber());
        dto.setMatchDate(match.getMatchDate());
        dto.setTeams(match.getTeams());
        dto.setApproxStartTime(match.getApproxStartTime());

        dto.setTossWinner(match.getTossWinner());
        dto.setMatchWinner(match.getMatchWinner());

        dto.setLeagueType(match.getLeagueType());
        dto.setMatchStatus(match.getMatchStatus());
        dto.setTeam1Score(match.getTeam1Score());
        dto.setTeam2Score(match.getTeam2Score());

        dto.setSessionDetails(parseSessionDetails(match.getSessionDetails()));

        return dto;
    }

    private List<SessionDetailDto> parseSessionDetails(String raw) {

        if (raw == null || raw.isBlank()) {
            return List.of();
        }

        String[] lines = raw.split("\\r?\\n");
        List<SessionDetailDto> list = new ArrayList<>();

        for (int i = 0; i < lines.length - 1; i += 2) {
            list.add(
                    new SessionDetailDto(
                            lines[i].trim(),
                            lines[i + 1].trim()
                    )
            );
        }
        return list;
    }

    // ✅ UPDATE MATCH DETAILS (POST API)
    public void updateMatchDetails(BplMatchUpdateRequestDto request) {

        Optional<SaT202026Match> optionalMatch =
                matchRepository.findByMatchNumber(request.getMatchNumber());

        if (optionalMatch.isEmpty()) {
            throw new RuntimeException(
                    "Match not found for match number: " + request.getMatchNumber()
            );
        }

        SaT202026Match match = optionalMatch.get();

        match.setTossWinner(request.getTossWinner());
        match.setMatchWinner(request.getMatchWinner());
        match.setTeam1Score(request.getTeam1Score());
        match.setTeam2Score(request.getTeam2Score());
        match.setSessionDetails(request.getSessionDetails());
        match.setMatchStatus(request.getMatchStatus());

        matchRepository.save(match);
    }
}

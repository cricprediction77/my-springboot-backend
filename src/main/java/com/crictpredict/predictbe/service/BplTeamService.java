package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.BplMatchDto;
import com.crictpredict.predictbe.dto.BplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.BplMatchesResponseDto;
import com.crictpredict.predictbe.dto.SessionDetailDto;
import com.crictpredict.predictbe.entity.Bpl20252026Match;
import com.crictpredict.predictbe.entity.BplTeam;
import com.crictpredict.predictbe.repository.Bpl20252026MatchRepository;
import com.crictpredict.predictbe.repository.BplTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BplTeamService {

    @Autowired
    private BplTeamRepository repository;

    @Autowired
    private Bpl20252026MatchRepository matchRepository;

    public BplMatchesResponseDto getBplMatchesWithTeams() {

        List<Bpl20252026Match> matches = matchRepository.findAll();
        List<BplTeam> teams = repository.findAll();

        List<BplMatchDto> matchDtos = matches.stream()
                .map(this::mapToMatchDto)
                .toList();

        BplMatchesResponseDto response = new BplMatchesResponseDto();
        response.setMatches(matchDtos);
        response.setTeams(teams);

        return response;
    }

    // ---------------- HELPERS ----------------

    private BplMatchDto mapToMatchDto(Bpl20252026Match match) {

        BplMatchDto dto = new BplMatchDto();

        dto.setMatchNumber(match.getMatchNumber());
        dto.setMatchDate(match.getMatchDate());
        dto.setTeams(match.getTeams());
        dto.setApproxStartTime(match.getApproxStartTime());

        dto.setTossWinner(match.getTossWinner());   // ✅ FIX
        dto.setMatchWinner(match.getMatchWinner()); // ✅ FIX

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
    public void updateMatchDetails(BplMatchUpdateRequestDto request) {

        try {
            Optional<Bpl20252026Match> optionalMatch =
                    matchRepository.findByMatchNumber(request.getMatchNumber());

            if (optionalMatch.isEmpty()) {
                throw new RuntimeException(
                        "Match not found for match number: " + request.getMatchNumber()
                );
            }

            Bpl20252026Match match = optionalMatch.get();

            // ✅ Update only fields coming from payload
            match.setTossWinner(request.getTossWinner());
            match.setMatchWinner(request.getMatchWinner());
            match.setTeam1Score(request.getTeam1Score());
            match.setTeam2Score(request.getTeam2Score());
            match.setSessionDetails(request.getSessionDetails());
            match.setMatchStatus(request.getMatchStatus()); // COMPLETED

            matchRepository.save(match);

        } catch (Exception e) {
            throw new RuntimeException("Error updating match details", e);
        }
    }

}


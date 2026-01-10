package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.SessionDetailDto;
import com.crictpredict.predictbe.dto.SuperSmashMatchDto;
import com.crictpredict.predictbe.dto.SuperSmashMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.SuperSmashMatchesResponseDto;
import com.crictpredict.predictbe.entity.SuperSmash20252026Match;
import com.crictpredict.predictbe.entity.SuperSmashTeam;
import com.crictpredict.predictbe.repository.SuperSmashMatchRepository;
import com.crictpredict.predictbe.repository.SuperSmashTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperSmashService {

    @Autowired
    private SuperSmashMatchRepository superSmashMatchRepository;

    @Autowired
    private SuperSmashTeamRepository superSmashTeamRepository;

    // ✅ GET API
    public SuperSmashMatchesResponseDto getSuperSmashMatchesWithTeams() {

        List<SuperSmash20252026Match> matches =
                superSmashMatchRepository.findAll(Sort.by(Sort.Direction.ASC, "matchNumber"));

        List<SuperSmashTeam> teams =
                superSmashTeamRepository.findAll();

        // ✅ Convert entity list -> dto list
        List<SuperSmashMatchDto> matchDtos = matches.stream()
                .map(this::mapToDto)
                .toList();

        SuperSmashMatchesResponseDto response = new SuperSmashMatchesResponseDto();
        response.setMatches(matchDtos);
        response.setTeams(teams);

        return response;
    }

    // ✅ POST API (no change needed)
    public void updateMatchDetails(SuperSmashMatchUpdateRequestDto request) {

        try {
            Optional<SuperSmash20252026Match> optionalMatch =
                    superSmashMatchRepository.findByMatchNumber(request.getMatchNumber());

            if (optionalMatch.isEmpty()) {
                throw new RuntimeException("Match not found: " + request.getMatchNumber());
            }

            SuperSmash20252026Match match = optionalMatch.get();

            match.setTossWinner(request.getTossWinner());
            match.setMatchWinner(request.getMatchWinner());
            match.setTeam1Score(request.getTeam1Score());
            match.setTeam2Score(request.getTeam2Score());

            // ✅ Stored as TEXT in DB
            match.setSessionDetails(request.getSessionDetails());

            match.setMatchStatus(request.getMatchStatus());

            superSmashMatchRepository.save(match);

        } catch (Exception e) {
            throw new RuntimeException("Error updating Super Smash match details", e);
        }
    }

    // ✅ mapper
    private SuperSmashMatchDto mapToDto(SuperSmash20252026Match match) {

        SuperSmashMatchDto dto = new SuperSmashMatchDto();

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

        // ✅ convert string to array for FE
        dto.setSessionDetails(parseSessionDetails(match.getSessionDetails()));

        return dto;
    }

    // ✅ Converts multiline TEXT sessionDetails -> List<SessionDetailDto>
    private List<SessionDetailDto> parseSessionDetails(String sessionDetails) {

        if (sessionDetails == null || sessionDetails.trim().isEmpty()) {
            return null; // FE fallback will show "Session details will be updated soon"
        }

        String[] lines = sessionDetails.split("\\r?\\n");
        List<SessionDetailDto> list = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {

            String sessionText = lines[i].trim();
            if (sessionText.isEmpty()) continue;

            SessionDetailDto dto = new SessionDetailDto();
            dto.setSessionText(sessionText);

            // ✅ next line maybe timestamp
            if (i + 1 < lines.length) {
                String next = lines[i + 1].trim();

                if (next.matches(".*\\d{4}-\\d{2}-\\d{2}.*")) {
                    dto.setUpdatedAt(next);
                    i++; // skip timestamp line
                }
            }

            list.add(dto);
        }

        return list;
    }
}

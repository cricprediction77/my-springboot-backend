package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.SessionDetailDto;
import com.crictpredict.predictbe.dto.WplMatchDto;
import com.crictpredict.predictbe.dto.WplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.WplMatchesResponseDto;
import com.crictpredict.predictbe.entity.Wpl2026Match;
import com.crictpredict.predictbe.entity.WplTeam;
import com.crictpredict.predictbe.repository.Wpl2026MatchRepository;
import com.crictpredict.predictbe.repository.WplTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WplService {

    @Autowired
    private Wpl2026MatchRepository wpl2026MatchRepository;

    @Autowired
    private WplTeamRepository wplTeamRepository;

    public WplMatchesResponseDto getWplMatchesWithTeams() {

        List<Wpl2026Match> matches =
                wpl2026MatchRepository.findAll(Sort.by(Sort.Direction.ASC, "matchNumber"));

        List<WplTeam> teams = wplTeamRepository.findAll();

        // ✅ convert entity matches to DTO matches
        List<WplMatchDto> matchDtos = matches.stream()
                .map(this::mapToDto)
                .toList();

        WplMatchesResponseDto response = new WplMatchesResponseDto();
        response.setMatches(matchDtos);
        response.setTeams(teams);

        return response;
    }

    // ✅ NEW: update match details from Admin
    public void updateMatchDetails(WplMatchUpdateRequestDto request) {

        try {
            Optional<Wpl2026Match> optionalMatch =
                    wpl2026MatchRepository.findByMatchNumber(request.getMatchNumber());

            if (optionalMatch.isEmpty()) {
                throw new RuntimeException("Match not found: " + request.getMatchNumber());
            }

            Wpl2026Match match = optionalMatch.get();

            match.setTossWinner(request.getTossWinner());
            match.setMatchWinner(request.getMatchWinner());
            match.setTeam1Score(request.getTeam1Score());
            match.setTeam2Score(request.getTeam2Score());

            // ✅ store in DB as TEXT
            match.setSessionDetails(request.getSessionDetails());

            match.setMatchStatus(request.getMatchStatus()); // COMPLETED

            wpl2026MatchRepository.save(match);

        } catch (Exception e) {
            throw new RuntimeException("Error updating WPL match details", e);
        }
    }

    // ✅ mapper
    private WplMatchDto mapToDto(Wpl2026Match match) {
        WplMatchDto dto = new WplMatchDto();

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

        // ✅ convert TEXT -> List for FE
        dto.setSessionDetails(parseSessionDetails(match.getSessionDetails()));

        return dto;
    }

    // ✅ converts multiline string into list objects
    private List<SessionDetailDto> parseSessionDetails(String sessionDetails) {

        if (sessionDetails == null || sessionDetails.trim().isEmpty()) {
            return null;
        }

        String[] lines = sessionDetails.split("\\r?\\n");
        List<SessionDetailDto> list = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String sessionText = lines[i].trim();
            if (sessionText.isEmpty()) continue;

            SessionDetailDto dto = new SessionDetailDto();
            dto.setSessionText(sessionText);

            // ✅ if next line exists and looks like time stamp, store as updatedAt
            if (i + 1 < lines.length) {
                String next = lines[i + 1].trim();

                // very simple detection: contains year-month-day
                if (next.matches(".*\\d{4}-\\d{2}-\\d{2}.*")) {
                    dto.setUpdatedAt(next);
                    i++; // ✅ skip updatedAt line
                }
            }

            list.add(dto);
        }

        return list;
    }
}

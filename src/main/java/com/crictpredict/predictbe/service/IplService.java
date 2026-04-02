package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.dto.IplMatchDto;
import com.crictpredict.predictbe.dto.IplMatchUpdateRequestDto;
import com.crictpredict.predictbe.dto.IplMatchesResponseDto;
import com.crictpredict.predictbe.dto.SessionDetailDto;
import com.crictpredict.predictbe.entity.Ipl2026Match;
import com.crictpredict.predictbe.repository.Ipl2026MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IplService {

    @Autowired
    private Ipl2026MatchRepository iplRepo;

    public IplMatchesResponseDto getIplMatchesWithTeams() {

        List<Ipl2026Match> matches =
                iplRepo.findAll(Sort.by(Sort.Direction.ASC, "matchNumber"));

        List<IplMatchDto> matchDtos = matches.stream()
                .map(this::mapToDto)
                .toList();

        IplMatchesResponseDto response = new IplMatchesResponseDto();
        response.setMatches(matchDtos);

        return response;
    }

    public void updateMatchDetails(IplMatchUpdateRequestDto request) {

        Ipl2026Match match = iplRepo
                .findByMatchNumber(request.getMatchNumber())
                .orElseThrow();

        match.setTossWinner(request.getTossWinner());
        match.setMatchWinner(request.getMatchWinner());
        match.setTeam1Score(request.getTeam1Score());
        match.setTeam2Score(request.getTeam2Score());
        match.setSessionDetails(request.getSessionDetails());
        match.setMatchStatus(request.getMatchStatus());

        iplRepo.save(match);
    }

    private IplMatchDto mapToDto(Ipl2026Match match) {

        IplMatchDto dto = new IplMatchDto();

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

        // ✅ FIX HERE
        dto.setSessionDetails(parseSessionDetails(match.getSessionDetails()));

        return dto;
    }

    private List<SessionDetailDto> parseSessionDetails(String sessionDetails) {

        if (sessionDetails == null || sessionDetails.isEmpty()) {
            return List.of();
        }

        List<SessionDetailDto> list = new ArrayList<>();

        String[] lines = sessionDetails.split("\\n");

        for (int i = 0; i < lines.length - 1; i += 2) {
            String sessionText = lines[i].trim();
            String updatedAt = lines[i + 1].trim();

            list.add(new SessionDetailDto(sessionText, updatedAt));
        }

        return list;
    }
}

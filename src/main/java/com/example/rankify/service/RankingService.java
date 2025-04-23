package com.example.rankify.service;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.model.Ranking;

import java.util.List;

public interface RankingService {

    Ranking createRanking(RankingDTO rankingDTO);

    List<RankingDTO> getAllRankings();

    RankingDTO getRankingById(Long id);




}

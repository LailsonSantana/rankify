package com.example.rankify.service;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Ranking;

import java.util.List;

public interface RankingService {

    Ranking createRanking(RankingDTO rankingDTO);

    List<RankingDTO> getAllRankings();

    RankingDTO getRankingById(Long id);

    void deleteRankingById(Long id);

    void updateRankingById(Long id, RankingDTO rankingDTO);




}

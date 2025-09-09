package com.example.rankify.service.impl;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.entity.Category;
import com.example.rankify.entity.Ranking;
import com.example.rankify.repository.RankingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RankingServiceImplTest {


    @InjectMocks
    private RankingServiceImpl rankingService;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private RankingMapper rankingMapper;

    @Test
    void getAllRankings() {
        Ranking ranking = new Ranking(1L , Category.BOOK , null , null);
        Mockito.when(rankingRepository.findAll()).thenReturn(Collections.singletonList(ranking));
        List<RankingDTO> rankingDTOS = rankingService.getAllRankings();
        System.out.println(rankingDTOS);
    }

}
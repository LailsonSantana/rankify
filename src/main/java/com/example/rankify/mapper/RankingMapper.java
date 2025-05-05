package com.example.rankify.mapper;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Ranking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RankingMapper {

    private final RankingEntityMapper rankingEntityMapper;

    public RankingDTO toRankingDTO(Ranking ranking){

        return new RankingDTO(
                ranking.getId(),
                ranking.getCategory().name(),
                ranking.getRankedEntities().stream().map(rankingEntityMapper::toDTO).toList(),
                ranking.getUser().getId(),
                ranking.getUser().getName()
        );
    }

    public List<RankingDTO> toRankingsDTO(List<Ranking> rankings){
        return rankings.stream().map(this::toRankingDTO).toList();
    }


}

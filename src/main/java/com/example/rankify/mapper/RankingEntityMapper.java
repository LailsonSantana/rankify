package com.example.rankify.mapper;

import com.example.rankify.dto.RankedEntityDTO;
import com.example.rankify.model.RankedEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class RankingEntityMapper {

    public RankedEntityDTO toRankedEntityDTO(RankedEntity rankedEntity){
        return new RankedEntityDTO(
                rankedEntity.getName(),
                rankedEntity.getRanking().getId()
        );
    }
}

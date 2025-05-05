package com.example.rankify.mapper;

import com.example.rankify.dto.RankedEntityDTO;
import com.example.rankify.entity.RankedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RankingEntityMapper {

    /*public RankedEntityDTO toRankedEntityDTO(RankedEntity rankedEntity){
        return new RankedEntityDTO(
                rankedEntity.getName(),
                rankedEntity.getRanking().getId()
        );
    }*/

    RankedEntityDTO toDTO(RankedEntity rankedEntity);
    RankedEntity toEntity(RankedEntityDTO rankedEntityDTO);
}

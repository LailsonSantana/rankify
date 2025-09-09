package com.example.rankify.mapper;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RankingMapper {

    RankingDTO toDTO(Ranking ranking);
    Ranking toRanking(RankingDTO rankingDTO);

    List<RankingDTO> toDTOs(List<Ranking> rankings);
    List<Ranking> toRankings(List<RankingDTO> rankingDTOs);
}

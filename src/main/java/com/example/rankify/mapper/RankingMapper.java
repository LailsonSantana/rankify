package com.example.rankify.mapper;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Item;
import com.example.rankify.entity.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RankingMapper {

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
    })
    RankingDTO toRankingDTO(Ranking ranking);

    @Mappings({
            @Mapping(source = "userId", target = "user.id"),
    })
    Ranking toRanking(RankingDTO rankingDTO);

    List<RankingDTO> toDTOs(List<Ranking> rankings);
    List<Ranking> toRankings(List<RankingDTO> rankingDTOs);




}

package com.example.rankify.dto;

import java.util.List;

public record UserDTO (Long id,
                       String name,
                       List<RankingDTO> rankingDTOS){
}

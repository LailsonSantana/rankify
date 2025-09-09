package com.example.rankify.dto;

import java.util.List;

public record UserDTO (Long id,
                       String name,
                       String email,
                       String password,
                       List<RankingDTO> rankingDTOS){
}

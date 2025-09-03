package com.example.rankify.dto;

import java.util.List;

public record ItemDTO(String name,
                      String category,
                      List<RankingDTO> rankingDTOS
                      ){
}

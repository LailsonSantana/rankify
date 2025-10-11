package com.example.rankify.dto;

import java.io.Serializable;
import java.util.List;

public record ItemDTO(Long id,
                      String name,
                      String category,
                      List<RankingDTO> rankingDTOS
                      ) implements Serializable {
}

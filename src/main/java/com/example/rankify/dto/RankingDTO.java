package com.example.rankify.dto;

import java.io.Serializable;
import java.util.List;

public record RankingDTO(Long id,
                         String category,
                         List<ItemDTO> itemsDTO,
                         Long userId
                         ) implements Serializable{
}

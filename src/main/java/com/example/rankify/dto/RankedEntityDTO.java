package com.example.rankify.dto;

import java.io.Serializable;

public record RankedEntityDTO(String name,
                              Long idRanking) implements Serializable {
}

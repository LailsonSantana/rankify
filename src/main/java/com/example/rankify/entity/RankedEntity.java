package com.example.rankify.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DB_ENTITY")
@Data
public class RankedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Ranking ranking;

}

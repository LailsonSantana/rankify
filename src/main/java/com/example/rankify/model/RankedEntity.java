package com.example.rankify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DB_ENTITY")
public class RankedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Ranking ranking;

}

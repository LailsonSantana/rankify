package com.example.rankify.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DB_RANKING")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Define how this attribute will be persistent on database
    @Column // This annotation is used to set up specific configurations to this attribute
    private Category category;

    @OneToMany(mappedBy = "ranking") // This name must be equal to attribute name put in Ranking class
    private List<RankedEntity> rankedEntities;

    @ManyToOne
    private User user;
}

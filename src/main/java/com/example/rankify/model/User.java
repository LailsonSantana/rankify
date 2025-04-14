package com.example.rankify.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Ranking> rankings;


}

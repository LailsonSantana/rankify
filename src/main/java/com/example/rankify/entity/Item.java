package com.example.rankify.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "DB_ITEM")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    @ManyToMany(mappedBy = "item")
    private List<Ranking> rankings;

}

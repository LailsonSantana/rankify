package com.example.rankify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DB_RANKING")
//@Builder // Allows to create an object with only some attributes
@AllArgsConstructor
@NoArgsConstructor
@Data // Allows to generate getters and setters automatically
@Builder
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Define how this attribute will be persistent on database
    @Column // This annotation is used to set up specific configurations to this attribute
    private Category category;

    @ManyToMany(mappedBy = "rankings") // This name must be equal to attribute name put in Ranking class
    // It indicate that this relationship is controlled for attribute  rankings , that is in Item
    @JsonIgnoreProperties("rankings")
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addItem(Item item){
        items.add(item);
    }


}

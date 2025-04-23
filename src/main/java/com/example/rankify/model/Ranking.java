package com.example.rankify.model;

import com.example.rankify.dto.RankingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "DB_RANKING")
//@Builder // Allows to create an object with only some attributes
@AllArgsConstructor
@NoArgsConstructor
@Data // Allows to generate getters and setters automatically
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

    public Ranking(RankingDTO rankingDTO){
        this.category = Category.valueOf(rankingDTO.category());
        this.rankedEntities = rankingDTO.rankingEntityDTO();
    }
}

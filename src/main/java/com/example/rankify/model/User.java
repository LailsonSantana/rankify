package com.example.rankify.model;

import com.example.rankify.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "DB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    @Column(nullable = true)
    private List<Ranking> rankings;

    public User(UserDTO userDTO){
        this.name = userDTO.name();
        this.rankings = null;
    }

}

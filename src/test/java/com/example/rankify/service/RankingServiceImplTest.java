package com.example.rankify.service;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.model.Category;
import com.example.rankify.model.Ranking;
import com.example.rankify.model.User;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AllArgsConstructor
class RankingServiceImplTest {

    private final EntityManager entityManager;

    @Mock // Mock is kind of a fake implementation of a class
    private final RankingRepository rankingRepository;

    @Mock
    private final UserRepository userRepository;

    @Mock
    private final RankingMapper rankingMapper;

    @InjectMocks
    private final RankingServiceImpl rankingService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Return ranking created with success")
    void createRanking() {
        User user = new User(1L , "Josep" , null);
        Ranking ranking = new Ranking(1L , Category.BOOK, null , user);
        //when(rankingRepository.save(ranking)).thenReturn();
    }

    @Test
    void getAllRankings() {
    }

    @Test
    void getRankingById() {
    }

    @Test
    void builder() {
    }

    private User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.entityManager.persist(newUser);
        return newUser;
    }
}
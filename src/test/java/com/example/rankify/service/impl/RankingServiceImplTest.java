package com.example.rankify.service.impl;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.User;
import com.example.rankify.excepiton.RankingNotFound;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.entity.Ranking;
import com.example.rankify.mapper.RankingMapperImpl;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import com.example.rankify.service.factory.RankingFactory;
import com.example.rankify.service.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RankingServiceImplTest {


    @InjectMocks
    private RankingServiceImpl rankingService;

    @Mock
    private RankingRepository rankingRepository;

    // This annotation allows to use real methods this class , it allows to create an
    // object partially mocked , in this case we don't need to simulate the behavior of this class
    @Spy
    private RankingMapper rankingMapper = new RankingMapperImpl();

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<Ranking> rankingArgumentCaptor;

    private Ranking ranking;
    private Ranking rankingUpdated;
    private RankingDTO rankingDTOUpdated;
    private RankingDTO rankingDTO;
    private User user;

    @BeforeEach
    void setUp(){
        this.ranking = RankingFactory.createFakeRanking();
        this.rankingUpdated = RankingFactory.createFakeRankingUpdated();
        this.rankingDTOUpdated = RankingFactory.createFakeRankingDTOUpdated();
        this.rankingDTO = RankingFactory.createFakeRankingDTO();
        this.user = UserFactory.createFakeUser();
    }

    @Nested
    class createRanking{

        @Test
        void shouldCreateRankingWithSuccess(){
            // Arrange
            Mockito.when(userRepository.findById(rankingDTO.id())).thenReturn(Optional.of(user));
            Mockito.when(rankingMapper.toRanking(rankingDTO)).thenReturn(ranking);

            // Act
            Mockito.doReturn(ranking).when(rankingRepository).save(rankingArgumentCaptor.capture());

            // Assert
            var output = rankingService.createRanking(rankingDTO);
            var rankingCaptured = rankingArgumentCaptor.getValue();

            assertEquals(output.getCategory(), rankingCaptured.getCategory());
            assertEquals(output.getUser(), rankingCaptured.getUser());
            assertEquals(output.getId(), rankingCaptured.getId());
        }

        @Test
        void  shouldReturnUserNotFoundWhenTryToCreateRanking(){

            // Assert
            Mockito.when(userRepository.findById(rankingDTO.id())).thenReturn(Optional.empty());

            // Act
            Executable action = () -> rankingService.createRanking(rankingDTO);

            // Assert
            assertThrows(UserNotFound.class , action);
        }
    }


    @Nested
    class getAllRankings{
        @Test
        void shouldReturnAllRankingsWithSuccess() {
            // In this example I'm going to use the real class mapper , instead of use a mock.
            // Arrange
            Mockito.when(rankingRepository.findAll()).thenReturn(Collections.singletonList(ranking));

            // Act
            var output = rankingService.getAllRankings();
            System.out.println("Saída" + output);

            // Assert
            assertEquals(1 , output.size());
        }

        @Test
        void shouldReturnAnEmptyList(){
            // Arrange
            Mockito.when(rankingRepository.findAll()).thenReturn(Collections.emptyList());

            // Act
            var output = rankingService.getAllRankings();

            // Assert
            assertTrue(output.isEmpty());

        }
    }

    @Nested
    class deleteRankingById{

        @Test
        void shouldDeleteARankingByIdWithSuccess(){
            // Arrange
            Mockito.when(rankingRepository.findById(ranking.getId())).thenReturn(Optional.of(ranking));

            // ArgumentMatchers.any(Ranking.class) : Ignore the attribute, just verify if this object
            // is a ranking
            Mockito.doNothing().when(rankingRepository).delete(ArgumentMatchers.any(Ranking.class));

            // Act - Assert
            assertDoesNotThrow(() -> rankingService.deleteRankingById(ranking.getId()));

        }

        @Test
        void shouldThrowAnExceptionWhenTryToDeleteARanking(){
            // Arrange
            Mockito.when(rankingRepository.findById(ranking.getId())).thenReturn(Optional.empty());

            Executable action = () -> rankingService.deleteRankingById(ranking.getId());

            // Assert
            assertThrows(RankingNotFound.class , action);
        }
    }

    @Nested
    class updateRankingById{

        @Test
        void shouldUpdateARankingWithSuccess(){
            // Arrange
            Mockito.when(rankingRepository.findById(ranking.getId())).thenReturn(Optional.of(ranking));
            Mockito.when(rankingRepository.save(ran))

            // Act
            Ranking result = rankingService.updateRankingById(ranking.getId(), rankingDTOUpdated);

            // Assert
            assertDoesNotThrow(() -> rankingService.updateRankingById(ranking.getId(),rankingDTO));


        }
    }
}
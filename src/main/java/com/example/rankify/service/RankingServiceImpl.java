package com.example.rankify.service;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.excepiton.RankingNotFound;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.model.Category;
import com.example.rankify.model.Ranking;
import com.example.rankify.model.User;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;
    private final RankingMapper rankingMapper;

    @Override
    @Transactional
    public Ranking createRanking(RankingDTO rankingDTO) {
        Optional<User> possibleUser = userRepository.findById(rankingDTO.id());
        Category category = Category.valueOf(rankingDTO.category().toUpperCase());

        if(possibleUser.isPresent()) {
            Ranking ranking = new Ranking();


            return rankingRepository.save(ranking);
        }
        throw new UserNotFound("User not found");
    }

    @Override
    public List<RankingDTO> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        return rankingMapper.toRankingsDTO(rankings);
    }

    @Override
    public RankingDTO getRankingById(Long id) {
        Optional<Ranking> possibleRanking = rankingRepository.findById(id);
        return possibleRanking.map(rankingMapper::toRankingDTO).orElseThrow();
    }
}

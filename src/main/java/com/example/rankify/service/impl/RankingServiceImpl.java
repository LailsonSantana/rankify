package com.example.rankify.service.impl;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.entity.Category;
import com.example.rankify.entity.Ranking;
import com.example.rankify.entity.User;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import com.example.rankify.service.RankingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;
    private final RankingMapper rankingMapper;

    @Override
    @Transactional
    public Ranking createRanking(RankingDTO rankingDTO) {
        Optional<User> possibleUser = userRepository.findById(rankingDTO.id());
        Category category = Category.valueOf(rankingDTO.category().toUpperCase());

        if(possibleUser.isPresent()) {
            Ranking ranking = rankingMapper.toRanking(rankingDTO);


            return rankingRepository.save(ranking);
        }
        throw new UserNotFound("User not found");
    }
    
    @Override
    public List<RankingDTO> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        return rankingMapper.toDTOs(rankings);
    }

    @Override
    public RankingDTO getRankingById(Long id) {
        Optional<Ranking> possibleRanking = rankingRepository.findById(id);
        return possibleRanking.map(rankingMapper::toDTO).orElseThrow();
    }
}

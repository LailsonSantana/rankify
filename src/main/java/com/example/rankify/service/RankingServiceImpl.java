package com.example.rankify.service;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.model.Category;
import com.example.rankify.model.Ranking;
import com.example.rankify.model.User;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;

    @Override
    public Ranking saveRanking(RankingDTO rankingDTO) {
        Optional<User> possibleUser = userRepository.findById(rankingDTO.id());
        Category category = Category.valueOf(rankingDTO.category().toUpperCase());

        if(possibleUser.isPresent()) {
            Ranking ranking = Ranking.builder()
                    .user(possibleUser.get())
                    .category(category).build();

            return rankingRepository.save(ranking);
        }
        throw new UserNotFound("User not found");
    }
}

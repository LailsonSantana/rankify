package com.example.rankify.service.impl;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Item;
import com.example.rankify.excepiton.RankingNotFound;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.ItemMapper;
import com.example.rankify.mapper.RankingMapper;
import com.example.rankify.entity.Category;
import com.example.rankify.entity.Ranking;
import com.example.rankify.entity.User;
import com.example.rankify.repository.ItemRepository;
import com.example.rankify.repository.RankingRepository;
import com.example.rankify.repository.UserRepository;
import com.example.rankify.service.RankingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@AllArgsConstructor
@Builder
@Slf4j
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final RankingMapper rankingMapper;
    private final ItemMapper itemMapper;

    @Override
    @Transactional
    public Ranking createRanking(RankingDTO rankingDTO) {
        Optional<User> possibleUser = userRepository.findById(rankingDTO.userId());

        if(possibleUser.isPresent()) {
            Ranking ranking = rankingMapper.toRanking(rankingDTO);
            ranking.setUser(possibleUser.get());

            log.info("COMPLETE RANKING {}" , ranking.getItems());
            for(ItemDTO itemDTO : rankingDTO.itemsDTO()){
                Item item = itemMapper.toItem(itemDTO);
                item.addRanking(ranking);
                ranking.addItem(item);
                itemRepository.save(item);
            }
            
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
        return possibleRanking.map(rankingMapper::toRankingDTO).orElseThrow();
    }

    @Override
    public void deleteRankingById(Long id) {
        Ranking ranking = rankingRepository.findById(id)
                .orElseThrow(() -> new RankingNotFound("Ranking not found"));
        rankingRepository.delete(ranking);
    }

    @Override
    @Transactional
    public void updateRankingById(Long id, RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking rankingToSave = rankingRepository.findById(id)
                .orElseThrow(() -> new RankingNotFound("Ranking not found"));

        rankingToSave.setCategory(ranking.getCategory());
        //rankingToSave.setItems(ranking.getItems());
        rankingToSave.setUser(ranking.getUser());
    }
}

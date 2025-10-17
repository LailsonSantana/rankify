package com.example.rankify.controller;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Ranking;
import com.example.rankify.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RankingDTO rankingDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(rankingService.createRanking(rankingDTO));
    }

    @GetMapping
    public ResponseEntity<List<RankingDTO>> getAllRankings(){
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getAllRankings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankingDTO> getRankingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getRankingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRankingById(@PathVariable Long id, @RequestBody RankingDTO rankingDTO ){
        rankingService.updateRankingById(id, rankingDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRankingById(@PathVariable Long id){
        rankingService.deleteRankingById(id);
        return ResponseEntity.noContent().build();
    }
}

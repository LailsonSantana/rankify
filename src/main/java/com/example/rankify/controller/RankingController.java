package com.example.rankify.controller;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Ranking;
import com.example.rankify.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @PostMapping
    public ResponseEntity<Ranking> create(@RequestBody RankingDTO rankingDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(rankingService.createRanking(rankingDTO));
    }
}

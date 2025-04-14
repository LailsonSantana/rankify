package com.example.rankify.repository;

import com.example.rankify.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}

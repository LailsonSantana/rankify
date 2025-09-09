package com.example.rankify.service.factory;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.entity.Category;
import com.example.rankify.entity.Item;
import com.example.rankify.entity.Ranking;
import com.example.rankify.entity.User;
import com.github.javafaker.Faker;

import java.util.Collections;

public class RankingFactory {

    public static RankingDTO createFakeRankingDTO(){
        return new RankingDTO(
                2L,
                String.valueOf(Category.MUSIC),
                null,
                1L
        );
    }

    /*private static Ranking createFakeRanking(){

        Ranking ranking = new Ranking();

        User user = UserFactory.createFakeUser(Collections.singletonList(ranking));
        Item item = new Item(
                2L,
                Faker.instance().music().instrument(),
                Category.MUSIC,
                Collections.singletonList(ranking)
        );

        ranking.setId(3L);
        ranking.setCategory(Category.MUSIC);
        ranking.setItems(Collections.singletonList(item));
        ranking.setUser(user);
        return ranking;
    }*/
}

package com.example.rankify.service.factory;

import com.example.rankify.dto.RankingDTO;
import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.Ranking;
import com.example.rankify.entity.User;
import com.github.javafaker.Faker;

import java.util.List;

public class UserFactory {

    private static final Faker faker = new Faker();

    public static User createFakeUser(){
        return new User(
                1L,
                faker.name().username(),
                faker.address().fullAddress(),
                faker.internet().password(),
                null
        );
    }

    public  static UserDTO createFakeUserDTO(){
        return new UserDTO(
                1L,
                faker.name().username(),
                faker.address().fullAddress(),
                faker.internet().password()
        );
    }
}

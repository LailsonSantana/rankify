package com.example.rankify.mapper;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapper {

    private final RankingMapper rankingMapper;

    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getName(), rankingMapper.toRankingsDTO(user.getRankings()));
    }
}

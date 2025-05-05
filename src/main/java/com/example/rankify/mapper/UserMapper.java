package com.example.rankify.mapper;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toDTO(User user);
    User toUser(UserDTO userDTO);


    List<UserDTO> toDTOs(List<User> users);
    List<User> toUsers(List<UserDTO> userDTOS);

}

package com.example.rankify.service;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.User;

import java.util.List;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
}

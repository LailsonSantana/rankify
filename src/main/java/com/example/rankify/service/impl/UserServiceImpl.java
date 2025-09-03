package com.example.rankify.service.impl;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.User;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.UserMapper;
import com.example.rankify.repository.UserRepository;
import com.example.rankify.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDTO createUser(UserDTO userDTO){
        User newUser = userMapper.toUser(userDTO);
        User userSaved = userRepository.save(newUser);
        return userMapper.toDTO(userSaved);
    }

    public List<UserDTO> getAllUsers(){
        return userMapper.toDTOs(userRepository.findAll());
    }

    public UserDTO getUserById(Long id){
        Optional<User> possibleUser = userRepository.findById(id);
        if(possibleUser.isPresent()){
            return userMapper.toDTO(possibleUser.get());
        }
        throw new UserNotFound("User not found");
    }
}

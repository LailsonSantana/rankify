package com.example.rankify.service.impl;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.User;
import com.example.rankify.mapper.UserMapper;
import com.example.rankify.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks // Annotation used in an original class instance
    private UserServiceImpl userService;

    @Mock // Annotation used to dependency of original class instance
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("Create a new user")
    void createUser() {
        // To create a test , is necessary three steps called of AAA
        // Arrange , set up test environment
        UserDTO userDTO = new UserDTO(3L , "Lailson" , null);
        User user = new User(3L, "Lailson", null);

        // It's used to inform your class what it must return in each case
        Mockito.when(userMapper.toUser(userDTO)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.toDTO(user)).thenReturn(userDTO);

        // Act , execute functionality that will be tested
        List<UserDTO> usersDTO = Collections.singletonList(userService.createUser(userDTO));
        //UserDTO result = userService.createUser(userDTO);

        // Assert , verify if the result is expected one
        assertEquals(1 , usersDTO.size());
        assertEquals(userDTO , usersDTO.getFirst());
    }

    @Test
    void getAllUsers() {

    }
}
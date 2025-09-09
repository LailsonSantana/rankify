package com.example.rankify.service.impl;

import com.example.rankify.dto.UserDTO;
import com.example.rankify.entity.User;
import com.example.rankify.excepiton.UserNotFound;
import com.example.rankify.mapper.UserMapper;
import com.example.rankify.repository.UserRepository;
import com.example.rankify.service.factory.UserFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class) // Process Mockito's annotations
class UserServiceImplTest {

    @InjectMocks // Annotation used in an original class instance
    private UserServiceImpl userService;

    @Mock // Annotation used to dependency of original class instance
    // When we request a mock object instance , the result is not real , then we need
    // Define what the mock should return
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> userIdArgumentCaptor;

    @Nested
    class createUser {
        @Test
        @DisplayName("Create a new user")
        void shouldCreateANewUser() {

            // To create a test , is necessary three steps called of AAA
            // Arrange , set up test environment
            var mockUserDTO = UserFactory.createFakeUserDTO();
            var mockUser = new User(mockUserDTO.id(),
                    mockUserDTO.name(),
                    mockUserDTO.email(),
                    mockUserDTO.password(),
                    null
            );

            // It's used to inform your class what it must return in each case
            // This simulates what will be returned
            Mockito.when(userMapper.toUser(mockUserDTO)).thenReturn(mockUser);
            Mockito.when(userRepository.save(mockUser)).thenReturn(mockUser);
            Mockito.when(userMapper.toDTO(mockUser)).thenReturn(mockUserDTO);

            // Act , execute functionality that will be tested
            List<UserDTO> usersDTO = Collections.singletonList(userService.createUser(mockUserDTO));
            //UserDTO result = userService.createUser(userDTO);

            // Assert , verify if the result is expected one
            assertEquals(1 , usersDTO.size());
            assertEquals(mockUserDTO , usersDTO.getFirst());
        }

        @Test
        @DisplayName("Create a new user")
        void shouldCreateANewUser2() {

            var mockUserDTO = UserFactory.createFakeUserDTO();
            var mockUser = new User(mockUserDTO.id(),
                    mockUserDTO.name(),
                    mockUserDTO.email(),
                    mockUserDTO.password(),
                    null
            );
            Mockito.doReturn(mockUser).when(userRepository).save(userArgumentCaptor.capture());

            // To create a test , is necessary three steps called of AAA
            // Arrange , set up test environment


            // It's used to inform your class what it must return in each case
            // This simulates what will be returned
            Mockito.when(userMapper.toUser(mockUserDTO)).thenReturn(mockUser);
            //Mockito.when(userRepository.save(mockUser)).thenReturn(mockUser);
            Mockito.when(userMapper.toDTO(mockUser)).thenReturn(mockUserDTO);

            var output = userService.createUser(mockUserDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(mockUser.getName(), userCaptured.getName());
            assertEquals(mockUser.getId(), mockUser.getId());

        }

        @Test
        @DisplayName("Returns an exception")
        void shouldReturnAnExceptionWhenErrorOccurs(){
            Mockito.doReturn(new RuntimeException()).when(userRepository).save(any());

            var mockUserDTO = UserFactory.createFakeUserDTO();
            var mockUser = new User(mockUserDTO.id(),
                    mockUserDTO.name(),
                    mockUserDTO.email(),
                    mockUserDTO.password(),
                    null
            );

            assertThrows(RuntimeException.class, () -> userService.createUser(mockUserDTO));
            //var output = userService.createUser(mockUserDTO);
        }
    }


    @Nested
    class getAllUsers{
        @Test
        void shouldReturnAllUsers() {
            // There are several ways to create data , like create fake data with factories , or you can
            // create data here.
            var mockUserDTO1 = UserFactory.createFakeUserDTO();
            var mockUser1 = new User(mockUserDTO1.id(),
                    mockUserDTO1.name(),
                    mockUserDTO1.email(),
                    mockUserDTO1.password(),
                    null
            );

            var mockUserDTO2 = UserFactory.createFakeUserDTO();
            var mockUser2 = new User(mockUserDTO2.id(),
                    mockUserDTO2.name(),
                    mockUserDTO2.email(),
                    mockUserDTO2.password(),
                    null
            );


            var users = List.of(mockUser1 , mockUser2);
            var usersDTO = List.of(mockUserDTO1 , mockUserDTO2);

            Mockito.when(userMapper.toDTOs(users)).thenReturn(usersDTO);

            var usersReturned = Mockito.when(userRepository.findAll()).thenReturn(users);

            var result = userService.getAllUsers();

            assertEquals(2 , result.size());
        }
    }

    @Nested
    class getUserById{
        @Test
        void shouldReturnUserByIdWhenOptionalIsPresent(){
            var mockUserDTO = UserFactory.createFakeUserDTO();
            var mockUser = new User(mockUserDTO.id(),
                    mockUserDTO.name(),
                    mockUserDTO.email(),
                    mockUserDTO.password(),
                    null
            );
            Mockito.when(userMapper.toDTO(mockUser)).thenReturn(mockUserDTO);

            Mockito.doReturn(Optional.of(mockUser)).when(userRepository).findById(userIdArgumentCaptor.capture());

            var output = userService.getUserById(mockUser.getId());

            assertEquals(output.id() , userIdArgumentCaptor.getValue());
        }

        @Test
        void shouldReturnUserNotFoundWhenOptionalIsEmpty(){
            // Arrange
            var userId = 2L;
            Mockito.doReturn(Optional.empty()).when(userRepository).findById(userIdArgumentCaptor.capture());

            // Act
            Executable action = () -> userService.getUserById(userId);

            // Assert
            assertThrows(UserNotFound.class, action);
            assertEquals(userId, userIdArgumentCaptor.getValue());
        }
    }

}
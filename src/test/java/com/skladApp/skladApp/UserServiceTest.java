package com.skladApp.skladApp.Service;

import com.skladApp.skladApp.Models.UserModel;
import com.skladApp.skladApp.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllUsers() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel("1", "user1", "password1"));
        users.add(new UserModel("2", "user2", "password2"));

        when(userRepository.findAll()).thenReturn(users);

        List<UserModel> result = userService.allUsers();
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testAddUser() {
        UserModel user = new UserModel("3", "user3", "password3");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        userService.addUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(passwordEncoder, times(1)).encode("password3");
        verify(userRepository, times(1)).insert(user);
    }

    @Test
    void testUpdateUser() {
        UserModel user = new UserModel("4", "user4", "password4");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        userService.updateUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(passwordEncoder, times(1)).encode("password4");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        UserModel user = new UserModel("5", "user5", "password5");
        doNothing().when(userRepository).delete(any(UserModel.class));

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        UserModel user = new UserModel("6", "user6", "password6");
        List<UserModel> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        org.springframework.security.core.userdetails.UserDetails result = userService.loadUserByUsername("user6");

        assertEquals("user6", result.getUsername());
        assertEquals("password6", result.getPassword());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        List<UserModel> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        UsernameNotFoundException thrown = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("user7")
        );
        assertEquals("User not found", thrown.getMessage());
    }
}

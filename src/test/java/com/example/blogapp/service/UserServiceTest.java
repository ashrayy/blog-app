package com.example.blogapp.service;

import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.model.User;
import com.example.blogapp.payload.UserDTO;
import com.example.blogapp.repository.RoleRepo;
import com.example.blogapp.repository.UserRepo;
import com.example.blogapp.service.impl.UserServiceImpl;
import com.example.blogapp.util.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UserRepo userRepo;

    @Mock
    RoleRepo roleRepo;

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    public void testDelete() {
        when(userRepo.getById(any())).thenReturn(CommonUtils.getDummyUserData());
        doNothing().when(userRepo).deleteById(any());
        userService.deleteUser(2);
        verify(userRepo, times(1)).deleteById(any());
    }

    @Test
    public void testDeleteIfNull() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(101);
        });
    }

    @Test
    public void saveUser() {
        User user = CommonUtils.getDummyUserData();
        when(modelMapper.map(Mockito.isA(UserDTO.class), Mockito.eq(User.class))).thenReturn(user);
        when(userRepo.save(any())).thenReturn(user);
        when(roleRepo.getById(any())).thenReturn(CommonUtils.getDummyRole());
        when(modelMapper.map(Mockito.isA(User.class), Mockito.eq(UserDTO.class))).thenReturn(CommonUtils.getDummyUserDTOData());
        UserDTO actualUserResponse = this.userService.createUser(CommonUtils.getDummyUserDTOData());
        assertEquals(actualUserResponse.getAbout(), user.getAbout());
        verify(roleRepo, times(1)).getById(any());
        verify(userRepo, times(1)).save(any());
    }

    @Test
    public void getAllUser() {
        when(userRepo.findAll()).thenReturn(CommonUtils.getDummyUserListData());
        userService.getUserList();
        verify(userRepo, times(1)).findAll();
    }
}

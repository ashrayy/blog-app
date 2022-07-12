package com.example.blogapp.service;

import com.example.blogapp.payload.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer id);

    UserDTO getUserById(Integer id);

    List<UserDTO> getUserList();

    void deleteUser(Integer id);

}

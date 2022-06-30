package com.example.easynotes.service;

import com.example.easynotes.model.User;
import com.example.easynotes.payload.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer id);

    UserDTO getUserById(Integer id);

    List<UserDTO> getUserList();

    void deleteUser(Integer id);

}

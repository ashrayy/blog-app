package com.example.easynotes.service.impl;

import com.example.easynotes.config.AppConstants;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Role;
import com.example.easynotes.model.User;
import com.example.easynotes.payload.UserDTO;
import com.example.easynotes.repository.CommentRepo;
import com.example.easynotes.repository.RoleRepo;
import com.example.easynotes.repository.UserRepo;
import com.example.easynotes.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);

        // set roles
        Role role =this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User savedtoUser = this.userRepo.save(user);
        return this.userToDto(savedtoUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
     user.setName(userDto.getName());
     System.out.println(userDto.getName());
        System.out.println(user.getName());
     user.setPassword(userDto.getPassword());
     user.setEmail(userDto.getEmail());
     user.setAbout(userDto.getAbout());
     User updatedUser = this.userRepo.save(user);
     return userToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList =users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
//        this.commentRepo.findByUser(user).forEach(val->this.commentRepo.deleteById(val.getId()));
        this.userRepo.deleteById(id);
    }

    private User dtoToUser(UserDTO userDTO){
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        User user = this.modelMapper.map(userDTO,User.class);
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setAbout(userDTO.getAbout());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO userToDto(User user){
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setAbout(user.getAbout());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}

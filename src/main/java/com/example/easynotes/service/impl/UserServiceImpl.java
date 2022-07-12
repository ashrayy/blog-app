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
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        User user = this.modelMapper.map(userDTO,User.class);

        // set roles
        Role role =this.roleRepo.getById(AppConstants.NORMAL_USER);
        user.getRoles().add(role);
        User savedtoUser = this.userRepo.save(user);
        return this.modelMapper.map(savedtoUser,UserDTO.class);
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
     return this.modelMapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return this.modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList =users.stream().map(user -> this.modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        User user = this.userRepo.getById(id);
        if(user == null){
            throw new ResourceNotFoundException("User","id",id);
        }
//        this.commentRepo.findByUser(user).forEach(val->this.commentRepo.deleteById(val.getId()));
        this.userRepo.deleteById(id);
    }

}

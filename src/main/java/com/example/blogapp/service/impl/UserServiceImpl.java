package com.example.blogapp.service.impl;

import com.example.blogapp.payload.UserDTO;
import com.example.blogapp.service.UserService;
import com.example.blogapp.config.AppConstants;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.model.Role;
import com.example.blogapp.model.User;
import com.example.blogapp.repository.CommentRepo;
import com.example.blogapp.repository.RoleRepo;
import com.example.blogapp.repository.UserRepo;
import com.example.blogapp.util.ModelTransformer;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RoleRepo roleRepo;
    
    @Autowired
    ModelTransformer modelTransformer;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        User user = modelTransformer.transformValues(userDTO,User.class);

        // set roles
        Role role =this.roleRepo.getById(AppConstants.NORMAL_USER);
        user.getRoles().add(role);
        User savedtoUser = this.userRepo.save(user);
        return modelTransformer.transformValues(savedtoUser,UserDTO.class);
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
     return modelTransformer.transformValues(updatedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return modelTransformer.transformValues(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList =users.stream().map(user -> modelTransformer.transformValues(user,UserDTO.class)).collect(Collectors.toList());
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

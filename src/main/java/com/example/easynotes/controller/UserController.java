package com.example.easynotes.controller;

import com.example.easynotes.model.User;
import com.example.easynotes.payload.ApiResponse;
import com.example.easynotes.payload.UserDTO;
import com.example.easynotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer id){
        UserDTO updatedUser = this.userService.updateUser(userDTO,id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleterUser(@PathVariable Integer id){
         this.userService.deleteUser(id);
         return new ResponseEntity<>(new ApiResponse("User with id -" + id + " deleted successfully", true),HttpStatus.OK);
    }
}

package com.example.blogapp.security;

import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return this.userRepo.findByEmail(userName).orElseThrow(()-> new ResourceNotFoundException("User","User-id",userName.toString()));
    }
}

package com.example.bhd.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomDetailService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
    public UserDetails loadUserById(Integer userId);
}

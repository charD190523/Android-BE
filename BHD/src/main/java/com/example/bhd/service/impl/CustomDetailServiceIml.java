package com.example.bhd.service.impl;

import com.example.bhd.entity.User;
import com.example.bhd.repository.UserRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.service.CustomDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomDetailServiceIml implements CustomDetailService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new CustomUserDetails(user);
    }



    public UserDetails loadUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return this.loadUserByUsername(user.getEmail());
    }
}

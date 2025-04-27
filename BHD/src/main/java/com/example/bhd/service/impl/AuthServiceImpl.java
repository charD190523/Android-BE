package com.example.bhd.service.impl;

import com.example.bhd.dto.request.SignInRequestDTO;
import com.example.bhd.dto.request.SignUpRequestDTO;
import com.example.bhd.entity.User;
import com.example.bhd.exception.CustomException;
import com.example.bhd.repository.AuthRepository;
import com.example.bhd.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public String register(SignUpRequestDTO requestDTO) {
        if (requestDTO.getPassword() == null || requestDTO.getEmail() == null || requestDTO.getName() == null) {
            return "Invalid input data";
        } else if (!requestDTO.getPassword().equals(requestDTO.getConfirmPassword())) {
            return "Password and confirm password do not match";
        }
        User user = User.builder()
                .name(requestDTO.getName())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .build();
        authRepository.save(user);
        return "User registered successfully";
    }

    public String login (SignInRequestDTO requestDTO) {
        User user = authRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() ->new CustomException(400, "Invalid username or password"));
        if (!user.getPassword().equals(requestDTO.getPassword())) {
            return "Invalid username or password";
        }
        return "Login successful";
    }
}

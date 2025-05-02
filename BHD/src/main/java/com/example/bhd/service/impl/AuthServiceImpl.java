package com.example.bhd.service.impl;

import com.example.bhd.dto.request.SignInRequestDTO;
import com.example.bhd.dto.request.SignUpRequestDTO;
import com.example.bhd.entity.User;
import com.example.bhd.exception.CustomException;
import com.example.bhd.repository.UserRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.security.jwt.JwtRequestProvider;
import com.example.bhd.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtRequestProvider jwtRequestProvider;
    private final PasswordEncoder passwordEncoder;

    public String register(SignUpRequestDTO requestDTO) {
        if (requestDTO.getPassword() == null || requestDTO.getEmail() == null || requestDTO.getFullName() == null) {
            return "Invalid input data";
        } else if (!requestDTO.getPassword().equals(requestDTO.getConfirmPassword())) {
            return "Password and confirm password do not match";
        }
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new CustomException(400, "Email already registed!");
        }
        User user = User.builder()
                .fullName(requestDTO.getFullName())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .email(requestDTO.getEmail())
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login (@Validated @RequestBody SignInRequestDTO loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return "Invalid username or password";
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return jwtRequestProvider.generateToken(userDetails);

    }

}

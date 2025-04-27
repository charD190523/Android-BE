package com.example.bhd.service;

import com.example.bhd.dto.request.SignInRequestDTO;
import com.example.bhd.dto.request.SignUpRequestDTO;

public interface AuthService {

    String register(SignUpRequestDTO requestDTO);

    String login(SignInRequestDTO requestDTO);

}

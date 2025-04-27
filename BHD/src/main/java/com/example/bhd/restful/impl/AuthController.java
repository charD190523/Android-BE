package com.example.bhd.restful.impl;

import com.example.bhd.dto.request.SignInRequestDTO;
import com.example.bhd.dto.request.SignUpRequestDTO;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.restful.AuthOperations;
import com.example.bhd.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthOperations {

    private final AuthService authService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<String>> register(SignUpRequestDTO requestDTO) {
        return ResponseEntity.ok(ResponseFactory.success(authService.register(requestDTO)));
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> login(SignInRequestDTO requestDTO) {
        return ResponseEntity.ok(ResponseFactory.success(authService.login(requestDTO)));
    }
}

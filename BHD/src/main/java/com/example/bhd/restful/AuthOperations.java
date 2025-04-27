package com.example.bhd.restful;

import com.example.bhd.dto.request.SignInRequestDTO;
import com.example.bhd.dto.request.SignUpRequestDTO;
import com.example.bhd.factory.GeneralResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/auth")
public interface AuthOperations {

    @PostMapping("/register")
    ResponseEntity<GeneralResponse<String>> register(@RequestBody @Valid SignUpRequestDTO requestDTO);

    @PostMapping("/login")
    ResponseEntity<GeneralResponse<String>> login(@RequestBody @Valid SignInRequestDTO requestDTO);
}

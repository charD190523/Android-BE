package com.example.bhd.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDTO {
    @NotEmpty(message = "Username cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}

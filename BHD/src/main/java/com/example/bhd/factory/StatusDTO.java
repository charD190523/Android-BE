package com.example.bhd.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private String code;
    private String message;
    private String responseTime;
    private String displayMessage;
}


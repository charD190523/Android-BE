package com.example.bhd.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final int statusCode;
    private final String message;

    public ErrorMessage toErrorMessage(){
        ErrorMessage errorMessage = new ErrorMessage(statusCode,message);
        return errorMessage;
    }
}

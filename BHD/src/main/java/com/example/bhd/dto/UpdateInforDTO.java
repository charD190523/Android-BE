package com.example.bhd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInforDTO {
    private String email;
    private String fullName;
    private String telephone;
    private String birthday;
    private String gender;
    private String province;
    private String address;
}

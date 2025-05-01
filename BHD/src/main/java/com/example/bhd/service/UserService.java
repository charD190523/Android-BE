package com.example.bhd.service;

import com.example.bhd.dto.UpdateInforDTO;

public interface UserService {

    String updateInfor(UpdateInforDTO requestDTO);

    UpdateInforDTO getInfor();

}

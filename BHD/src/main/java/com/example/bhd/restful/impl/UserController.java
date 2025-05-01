package com.example.bhd.restful.impl;

import com.example.bhd.dto.UpdateInforDTO;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.restful.UserOperations;
import com.example.bhd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserOperations {

    private final UserService userService;

    @Override
    public ResponseEntity<GeneralResponse<String>> updateInfor(UpdateInforDTO requestDTO) {
        return ResponseEntity.ok(ResponseFactory.success(userService.updateInfor(requestDTO)));
    }

    @Override
    public ResponseEntity<GeneralResponse<UpdateInforDTO>> getInfor() {
        return ResponseEntity.ok(ResponseFactory.success(userService.getInfor()));
    }

}

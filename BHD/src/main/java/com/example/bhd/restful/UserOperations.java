package com.example.bhd.restful;

import com.example.bhd.dto.UpdateInforDTO;
import com.example.bhd.factory.GeneralResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
public interface UserOperations {

    @PostMapping("/update-infor")
    ResponseEntity<GeneralResponse<String>> updateInfor(@RequestBody @Valid UpdateInforDTO requestDTO);

    @GetMapping("/get-infor")
    ResponseEntity<GeneralResponse<UpdateInforDTO>> getInfor();
}

package com.example.bhd.service.impl;

import com.example.bhd.dto.UpdateInforDTO;
import com.example.bhd.entity.User;
import com.example.bhd.exception.CustomException;
import com.example.bhd.repository.UserRepository;
import com.example.bhd.security.CustomUserDetails;
import com.example.bhd.security.jwt.JwtRequestProvider;
import com.example.bhd.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtRequestProvider jwtRequestProvider;
    private final ObjectMapper objectMapper;

    @Override
    public String updateInfor(UpdateInforDTO updateUserDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() -> new CustomException(400, "User's credential not found!"));
            if (Objects.nonNull(updateUserDTO.getEmail()) && !updateUserDTO.getEmail().isEmpty()) {
                user.setEmail(updateUserDTO.getEmail());
            }
            if (Objects.nonNull(updateUserDTO.getFullName()) && !updateUserDTO.getFullName().isEmpty()) {
                user.setFullName(updateUserDTO.getFullName());
            }
            if (Objects.nonNull(updateUserDTO.getTelephone()) && !updateUserDTO.getTelephone().isEmpty()) {
                user.setTelephone(updateUserDTO.getTelephone());
            }
            if (Objects.nonNull(updateUserDTO.getBirthday())) {
                user.setBirthday(updateUserDTO.getBirthday());
            }
            if (Objects.nonNull(updateUserDTO.getGender()) && !updateUserDTO.getGender().isEmpty()) {
                user.setGender(updateUserDTO.getGender());
            }
            if (Objects.nonNull(updateUserDTO.getProvince()) && !updateUserDTO.getProvince().isEmpty()) {
                user.setProvince(updateUserDTO.getProvince());
            }
            if (Objects.nonNull(updateUserDTO.getAddress()) && !updateUserDTO.getAddress().isEmpty()) {
                user.setAddress(updateUserDTO.getAddress());
            }
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException(400, e.getMessage());
        }
        return "User information updated successfully";
    }

    @Override
    public UpdateInforDTO getInfor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() -> new CustomException(400, "User's credential not found!"));
        return UpdateInforDTO.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .telephone(user.getTelephone())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .province(user.getProvince())
                .address(user.getAddress())
                .build();
    }
}

package com.example.bhd.service.impl;

import com.example.bhd.entity.Food;
import com.example.bhd.repository.FoodRepository;
import com.example.bhd.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;


    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }
}

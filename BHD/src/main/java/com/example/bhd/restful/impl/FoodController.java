package com.example.bhd.restful.impl;

import com.example.bhd.entity.Food;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.restful.FoodOperations;
import com.example.bhd.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController implements FoodOperations {

    private final FoodService foodService;

    @Override
    public ResponseEntity<GeneralResponse<List<Food>>> getAllFood() {
        return ResponseEntity.ok(ResponseFactory.success(foodService.getAllFood()));
    }



}

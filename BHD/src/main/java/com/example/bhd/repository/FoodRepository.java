package com.example.bhd.repository;

import com.example.bhd.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findById(int id);
    Food findByPrice(double price);
}

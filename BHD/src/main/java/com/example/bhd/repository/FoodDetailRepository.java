package com.example.bhd.repository;

import com.example.bhd.entity.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Integer> {
    // Custom query methods can be defined here if needed
}

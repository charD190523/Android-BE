package com.example.bhd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "food")
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "price")
    private Float price;

    @Column(name = "description")
    private String description;

    @OneToMany (mappedBy = "food", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FoodDetail> foodDetails;
}

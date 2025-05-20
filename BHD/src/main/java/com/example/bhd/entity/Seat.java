package com.example.bhd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Float price;

    @JsonIgnore
    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    private List<SeatDetail> seatDetails;
}

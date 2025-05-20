package com.example.bhd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "seat_detail")
@AllArgsConstructor
@NoArgsConstructor
public class SeatDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;
}

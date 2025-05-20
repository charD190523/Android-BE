package com.example.bhd.entity;

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
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private List<Showtime> showtimes;
}

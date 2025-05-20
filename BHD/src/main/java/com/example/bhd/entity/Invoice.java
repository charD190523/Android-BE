package com.example.bhd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<FoodDetail> foodDetails;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public void setTicketList(List<Ticket> tickets) {
        this.tickets = tickets;
        if (tickets != null) {
            tickets.forEach(ticket -> ticket.setInvoice(this));
        }
    }

    public void setFoodDetailList(List<FoodDetail> foodDetails) {
        this.foodDetails = foodDetails;
        if (foodDetails != null) {
            foodDetails.forEach(foodDetail -> foodDetail.setInvoice(this));
        }
    }

    public void calculateTotalAmount() {
        // Tính tổng giá vé
        float ticketTotal = (tickets != null && !tickets.isEmpty())
                ? tickets.stream().map(Ticket::getPrice).reduce(0.0f, Float::sum)
                : 0.0f;

        // Tính tổng giá đồ ăn
        float foodTotal = (foodDetails != null && !foodDetails.isEmpty())
                ? foodDetails.stream()
                .map(fd -> fd.getFood().getPrice() * fd.getQuantity())
                .reduce(0.0f, Float::sum)
                : 0.0f;

        // Tổng tiền
        this.totalPrice = ticketTotal + foodTotal;
    }

}

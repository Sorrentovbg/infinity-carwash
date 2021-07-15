package ru.infinitycarwash.msorder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "order_tb")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_Id")
    private Long productId;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "data")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Order(Long userId, Long productId, String carNumber, LocalDate date, LocalTime time) {
        this.userId = userId;
        this.productId = productId;
        this.carNumber = carNumber;
        this.date = date;
        this.time = time;
    }
}

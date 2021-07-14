package ru.infinitycarwash.msorder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "order_tb")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "data")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    public Order(String customerName, String carNumber, String productName, LocalDate date, LocalTime time) {
        this.customerName = customerName;
        this.carNumber = carNumber;
        this.productName = productName;
        this.date = date;
        this.time = time;
    }
}

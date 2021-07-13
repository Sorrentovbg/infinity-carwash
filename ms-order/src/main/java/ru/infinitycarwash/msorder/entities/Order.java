package ru.infinitycarwash.msorder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_Number")
    private int phoneNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    @Column(name = "time")
    private String time;


}

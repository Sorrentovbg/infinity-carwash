package ru.infinitycarwash.msproduct.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_tb")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_short_description")
    private String productShortDescription;

    @Column(name = "product_full_description")
    private String productFullDescription;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "job_duration")
    private int jobDuration;

}

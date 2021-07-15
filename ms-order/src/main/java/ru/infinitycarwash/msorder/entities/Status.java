package ru.infinitycarwash.msorder.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "status_tb")
@Data


public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;
}

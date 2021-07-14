package ru.infinitycarwash.msauth.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_tb")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}

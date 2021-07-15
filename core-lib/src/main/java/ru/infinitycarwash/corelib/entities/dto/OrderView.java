package ru.infinitycarwash.corelib.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderView {

    private Long id;

    private String productName;

    private String carNumber;

    private LocalDate date;

    private LocalTime time;

}

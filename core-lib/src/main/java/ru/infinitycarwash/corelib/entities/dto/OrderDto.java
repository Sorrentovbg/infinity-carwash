package ru.infinitycarwash.corelib.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OrderDto {

    private String carNumber;

    private Long productId;

    private LocalDate date;

    private LocalTime time;

}

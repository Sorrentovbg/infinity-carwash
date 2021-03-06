package ru.infinitycarwash.corelib.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String productName;

    private int jobDuration;
}

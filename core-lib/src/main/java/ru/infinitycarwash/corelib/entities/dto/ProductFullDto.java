package ru.infinitycarwash.corelib.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductFullDto {

    private Long id;


    private String productName;

    private String productFullDescription;

    private int productPrice;

    private int jobDuration;
}

package ru.infinitycarwash.corelib.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private String productName;

    private String productDescription;

    private int productPrice;
}

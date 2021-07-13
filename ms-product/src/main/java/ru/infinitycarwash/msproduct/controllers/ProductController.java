package ru.infinitycarwash.msproduct.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;
import ru.infinitycarwash.msproduct.entities.Product;
import ru.infinitycarwash.msproduct.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/infinity/home")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProduct(){
        return productService.getAllProduct();
    }
}

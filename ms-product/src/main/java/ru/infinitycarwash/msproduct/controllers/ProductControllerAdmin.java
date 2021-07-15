package ru.infinitycarwash.msproduct.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.infinitycarwash.msproduct.entities.Product;
import ru.infinitycarwash.msproduct.services.ProductService;


@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/infinity/product/admin")
@RequiredArgsConstructor
@Api(value = "/ProductAdmin", tags = {"Создание и удаление продуктов"})
public class ProductControllerAdmin {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Добавить новый продукт",
            httpMethod = "POST"
    )
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }


    @PostMapping("/delProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Удалить продукт",
            httpMethod = "POST"
    )
    public void deleteFromProduct(@RequestParam(name = "id") Long id){
        productService.deleteProduct(id);
    }
}

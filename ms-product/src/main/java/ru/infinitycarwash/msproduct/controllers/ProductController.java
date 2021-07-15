package ru.infinitycarwash.msproduct.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;
import ru.infinitycarwash.corelib.entities.dto.ProductFullDto;
import ru.infinitycarwash.corelib.entities.dto.ProductShortDto;
import ru.infinitycarwash.msproduct.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/infinity/product/home")
@RequiredArgsConstructor
@Api(value = "/Product", tags = {"Получение и продуктов"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    @ApiOperation(
            value = "Получить все существующие продукты",
            httpMethod = "GET",
            response = List.class
    )
    public List<ProductShortDto> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/getProduct/{id}")
    @ApiOperation(
            value = "Получить продукт по ID",
            httpMethod = "GET",
            response = ProductFullDto.class
    )
    public ProductFullDto getOneProduct(@PathVariable Long id){
        return productService.getOneProduct(id);}

    @ApiOperation(
            value = "Получить продукт по ID для FeignClient",
            httpMethod = "GET",
            response = ProductDto.class
    )
    @GetMapping("/getProductDTO")
    public ProductDto getProductDto(@RequestParam(name = "id") Long id){
        return productService.getProductDto(id);
    }


}

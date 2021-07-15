package ru.infinitycarwash.eurekafeign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;

@FeignClient(name = "ms-product")
public interface ProductFeign {
    @GetMapping("/infinity/product/home/getProductDTO")
    ProductDto getProductDto(@RequestParam(name = "id") Long id);
}

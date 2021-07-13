package ru.infinitycarwash.msproduct.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;
import ru.infinitycarwash.msproduct.entities.Product;
import ru.infinitycarwash.msproduct.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productListDto = new ArrayList<>();
        for (Product product:productList) {
            productListDto.add(toDto(product));
        }
        return productListDto;
    }

    public ProductDto toDto (Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}

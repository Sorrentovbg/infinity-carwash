package ru.infinitycarwash.msproduct.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;
import ru.infinitycarwash.corelib.entities.dto.ProductFullDto;
import ru.infinitycarwash.corelib.entities.dto.ProductShortDto;
import ru.infinitycarwash.msproduct.entities.Product;
import ru.infinitycarwash.msproduct.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<ProductShortDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductShortDto> productListDto = new ArrayList<>();
        for (Product product:productList) {
            productListDto.add(toShortDto(product));
        }
        return productListDto;
    }

    public ProductFullDto getOneProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductFullDto productFullDto;
        if(product.isPresent()){
            productFullDto = toFullDto(product.get());
        }else{
            throw new RuntimeException("Product already exists");
        }
        return productFullDto;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }else {
            throw new NullPointerException("Product not found");
        }
    }

    public ProductDto getProductDto(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return toDto(product.get());
    }

    public ProductShortDto toShortDto (Product product){
        return modelMapper.map(product, ProductShortDto.class);
    }

    public ProductFullDto toFullDto (Product product){
        return modelMapper.map(product, ProductFullDto.class);
    }

    public ProductDto toDto (Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}

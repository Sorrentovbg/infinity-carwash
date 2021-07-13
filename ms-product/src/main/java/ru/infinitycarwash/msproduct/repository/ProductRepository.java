package ru.infinitycarwash.msproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.infinitycarwash.msproduct.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

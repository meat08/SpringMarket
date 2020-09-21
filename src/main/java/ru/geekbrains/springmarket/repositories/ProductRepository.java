package ru.geekbrains.springmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springmarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getProductByPriceGreaterThanEqual(Float min, Pageable var);

    Page<Product> getProductByPriceLessThanEqual(Float max, Pageable var);

    Page<Product> getProductByPriceGreaterThanEqualAndPriceLessThanEqual(Float max, Float min, Pageable var);
}

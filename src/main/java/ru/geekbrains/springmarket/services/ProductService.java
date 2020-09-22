package ru.geekbrains.springmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> getMaxPrice(int page, int size, Float max) {
        return productRepository.getProductByPriceGreaterThanEqual(max, PageRequest.of(page, size));
    }

    public Page<Product> getMinPrice(int page, int size, Float min) {
        return productRepository.getProductByPriceLessThanEqual(min, PageRequest.of(page, size));
    }

    public Page<Product> getMinAndMaxPrice(int page, int size, Float min, Float max) {
        return productRepository.getProductByPriceGreaterThanEqualAndPriceLessThanEqual(min, max, PageRequest.of(page, size));
    }
}

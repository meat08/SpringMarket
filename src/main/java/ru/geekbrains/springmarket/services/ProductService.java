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

    public Page<Product> getMaxPrice(int page, int size) {
        return productRepository.getProductByMaxPricePage(PageRequest.of(page, size));
    }

    public Page<Product> getMinPrice(int page, int size) {
        return productRepository.getProductByMinPricePage(PageRequest.of(page, size));
    }

    public Page<Product> getMinAndMaxPrice(int page, int size) {
        return productRepository.getProductByMinAndMaxPriceList(PageRequest.of(page, size));
    }
}

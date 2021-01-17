package ru.geekbrains.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.entities.dto.ProductDto;
import ru.geekbrains.springmarket.repositories.ProductRepository;
import ru.geekbrains.springmarket.soap.products.ProductSoap;
import ru.geekbrains.springmarket.utils.MyPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private Map<Long, Optional<Product>> productMap;

    @Autowired
    public void setProductMap() {
        productMap = new HashMap<>();
    }

    public MyPage<ProductDto> findAll(Specification<Product> spec, int page, int size) {
        Page<Product> content = productRepository.findAll(spec, PageRequest.of(page, size));
        return new MyPage<>(content.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
    }

    public List<ProductSoap> findAllSOAP() {
        return productRepository.findAll().stream().map(this::mapToSoap).collect(Collectors.toList());
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByIdIdentity(Long id) {
        Optional<Product> product;
        if (productMap.isEmpty() || productMap.get(id).isEmpty()) {
            product = productRepository.findById(id);
            productMap.put(id, product);
            return product;
        }
        return productMap.get(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    private ProductSoap mapToSoap(Product product) {
        ProductSoap soapProduct = new ProductSoap();
        soapProduct.setId(product.getId());
        soapProduct.setPrice(product.getPrice());
        soapProduct.setTitle(product.getTitle());
        soapProduct.setCategory(product.getCategory().getTitle());
        return soapProduct;
    }
}

package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.services.ProductService;
import ru.geekbrains.springmarket.utils.ProductFilter;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                        @RequestParam(defaultValue = "5", name = "s") Integer size,
                                        @RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAll(productFilter.getSpec(), page, size);
    }

}

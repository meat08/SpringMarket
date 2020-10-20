package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.springmarket.services.ProductService;
import ru.geekbrains.springmarket.utils.ProductFilter;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping(produces = "application/json")
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                        @RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAll(productFilter.getSpec(), page - 1, 5);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.save(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.save(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}

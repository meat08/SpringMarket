package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.services.ProductService;
import ru.geekbrains.springmarket.utils.ProductFilter;
import java.util.Map;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private static final int PAGE_SIZE = 5;
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page-1, PAGE_SIZE);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping("/edit_product/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "update_product";
    }

    @PostMapping("/update/{id}")
    public String updateUser(Product product) {
        productService.save(product);
        return "redirect:/products";
    }
}

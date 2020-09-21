package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.springmarket.services.ProductService;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private static final int PAGE_SIZE = 5;
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page, @RequestParam(required = false) Float min, @RequestParam(required = false) Float max) {
        if (page < 1) {
            page = 1;
        }
        if (min != null & max == null) {
            model.addAttribute("products", productService.getMaxPrice(page-1, PAGE_SIZE, min));
        } else if (min == null & max != null) {
            model.addAttribute("products", productService.getMinPrice(page-1, PAGE_SIZE, max));
        } else if (min != null & max != null) {
            model.addAttribute("products", productService.getMinAndMaxPrice(page-1, PAGE_SIZE, min, max));
        } else {
            model.addAttribute("products", productService.findAll(page-1, PAGE_SIZE));
        }
        return "products";
    }

    @PostMapping("/filter_product")
    public String filterProductByPrice(@RequestParam Float min, @RequestParam Float max) {
        if (min != null & max == null) {
            return "redirect:/products?min="+min;
        } else if (min == null & max != null) {
            return "redirect:/products?max="+max;
        } else if (min != null & max != null) {
            return "redirect:/products?max="+max+"&min="+min;
        } else {
            return "redirect:/products";
        }
    }
}

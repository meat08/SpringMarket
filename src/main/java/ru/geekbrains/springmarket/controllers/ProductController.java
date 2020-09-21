package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.springmarket.services.ProductService;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private static final int PAGE_SIZE = 5;
    private ProductService productService;

    //В каждом методе один и тот же RequestParam page и повторяется один и тот же if
    //Можно это как-то вынести, или упростить?
    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page-1, PAGE_SIZE));
        return "products";
    }

    @GetMapping("/max_price")
    public String showProductWithMaxPrice(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.getMaxPrice(page-1, PAGE_SIZE));
        return "products";
    }

    @GetMapping("/min_price")
    public String showProductWithMinPrice(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.getMinPrice(page-1, PAGE_SIZE));
        return "products";
    }

    @GetMapping("/min_max_price")
    public String showProductWithMinAndMaxPrice(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.getMinAndMaxPrice(page-1, PAGE_SIZE));
        return "products";
    }
}

package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.services.OrderService;
import ru.geekbrains.springmarket.services.UserService;
import ru.geekbrains.springmarket.utils.Cart;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/create_order")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("cart", cart);
        return "create_order";
    }

    @PostMapping("/save_order")
    public String saveOrder(Order order, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        order.setItems(cart.getItems());
        order.setPrice(cart.getPrice());
        order.setUser(user);
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}

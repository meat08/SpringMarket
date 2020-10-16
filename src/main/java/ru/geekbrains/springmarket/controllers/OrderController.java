package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("username", principal.getName());
        model.addAttribute("orders", orderService.findByUser(user));
        return "orders";
    }

    @GetMapping("/create_order")
    public String createOrder(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "create_order";
    }

    @PostMapping("/save_order")
    public String saveOrder(Principal principal,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "phone") Long phone,
                            @RequestParam(name = "address") String address) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(cart.getItems(), user, cart.getPrice(), name, phone, address);
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}

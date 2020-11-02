package ru.geekbrains.springmarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.OrderDto;
import ru.geekbrains.springmarket.services.OrderService;
import ru.geekbrains.springmarket.services.UserService;
import ru.geekbrains.springmarket.utils.Cart;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Cart cart;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> getOrdersByUser(Principal principal) {
        return orderService.findByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void createOrder(Principal principal,
                            @RequestParam(name = "customerName") String name,
                            @RequestParam(name = "customerPhone") Long phone,
                            @RequestParam(name = "customerAddress") String address) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(cart.getItems(), user, cart.getPrice(), name, phone, address);
        orderService.save(order);
        cart.clear();
    }
}

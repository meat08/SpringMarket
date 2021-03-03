package ru.geekbrains.springmarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.OrderDto;
import ru.geekbrains.springmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.springmarket.services.OrderService;
import ru.geekbrains.springmarket.services.UserService;
import ru.geekbrains.springmarket.utils.Cart;
import ru.geekbrains.springmarket.utils.MyPage;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Cart cart;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ORDERS')")
    public MyPage<OrderDto> getOrdersByUser(Principal principal,
                                            @RequestParam(defaultValue = "1", name = "p") Integer page) {
        return orderService.findByUsername(principal.getName(), page - 1, 5);
    }

    @PostMapping("/create")
    public void createOrder(Principal principal,
                            @RequestParam(name = "customerName") String name,
                            @RequestParam(name = "customerPhone") Long phone,
                            @RequestParam(name = "customerAddress") String address) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User '%s' not found", principal.getName())));
        Order order = new Order(cart.getItems(), user, cart.getPrice(), name, phone, address);
        orderService.save(order);
        cart.clear();
    }
}

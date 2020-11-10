package ru.geekbrains.springmarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.OrderDto;
import ru.geekbrains.springmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.springmarket.services.OrderService;
import ru.geekbrains.springmarket.services.UserService;
import ru.geekbrains.springmarket.utils.Cart;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Cart cart;
    private final UserService userService;

    @GetMapping
    public Page<OrderDto> getOrdersByUser(Principal principal,
                                          @RequestParam(defaultValue = "1", name = "p") Integer page) {
        Page<Order> content = orderService.findByUsername(principal.getName(), page - 1, 5);
        return new PageImpl<>(content.getContent().stream().map(OrderDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
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

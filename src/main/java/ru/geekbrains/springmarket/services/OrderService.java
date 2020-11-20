package ru.geekbrains.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.OrderItem;
import ru.geekbrains.springmarket.repositories.OrderRepository;
import ru.geekbrains.springmarket.soap.orders.OrderItemsSoap;
import ru.geekbrains.springmarket.soap.orders.OrderSoap;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findByUsername(String username, int page, int size) {
        return orderRepository.findAllOrdersByUsername(username, PageRequest.of(page, size));
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<OrderSoap> findSoapByUsername(String username) {
        return orderRepository.findAllOrdersByUsernameSoap(username).stream().map(this::mapOrderToSoap).collect(Collectors.toList());
    }

    private OrderSoap mapOrderToSoap(Order order) {
        OrderSoap orderSoap = new OrderSoap();
        orderSoap.setId(order.getId());
        orderSoap.setPrice(order.getPrice());
        orderSoap.setRecipientAddress(order.getRecipientAddress());
        orderSoap.setRecipientName(order.getRecipientName());
        orderSoap.setRecipientPhone(order.getRecipientPhone());
        orderSoap.setUsername(order.getUser().getUsername());
        orderSoap.getOrderItems().addAll(order.getItems().stream().map(this::mapOrderItemToSoap).collect(Collectors.toList()));
        return orderSoap;
    }

    private OrderItemsSoap mapOrderItemToSoap(OrderItem orderItem) {
        OrderItemsSoap orderItemsSoap = new OrderItemsSoap();
        orderItemsSoap.setPrice(orderItem.getPrice());
        orderItemsSoap.setQuantity(orderItem.getQuantity());
        orderItemsSoap.setProduct(orderItem.getProduct().getTitle());
        return orderItemsSoap;
    }

}

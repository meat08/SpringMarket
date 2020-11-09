package ru.geekbrains.springmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findByUsername(String username, int page, int size) {
        return orderRepository.findAllOrdersByUsername(username, PageRequest.of(page, size));
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

}

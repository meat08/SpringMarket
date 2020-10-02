package ru.geekbrains.springmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.OrderItem;
import ru.geekbrains.springmarket.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        //костыль, чтобы связать order и orderItems. Может можно как-то проще?
        for (OrderItem o : order.getItems()) {
            o.setOrder(order);
        }
        orderRepository.save(order);
    }

}

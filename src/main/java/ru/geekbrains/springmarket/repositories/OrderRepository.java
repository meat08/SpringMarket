package ru.geekbrains.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findAllOrdersByUsername(String username);
}

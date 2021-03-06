package ru.geekbrains.springmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springmarket.entities.Order;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username = ?1")
    Page<Order> findAllOrdersByUsername(String username, Pageable pageable);

    @Query("select distinct o from Order o join fetch o.items where o.user.username = ?1")
    List<Order> findAllOrdersByUsernameSoap(String username);
}

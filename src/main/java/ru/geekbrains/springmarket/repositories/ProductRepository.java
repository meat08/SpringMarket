package ru.geekbrains.springmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springmarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price = (SELECT MAX(p1.price) FROM Product p1)")
    Page<Product> getProductByMaxPricePage(Pageable var);

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT MIN(p1.price) FROM Product p1)")
    Page<Product> getProductByMinPricePage(Pageable var);

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT MAX(p1.price) FROM Product p1)" +
            "OR p.price = (SELECT MIN(p1.price) FROM Product p1)")
    Page<Product> getProductByMinAndMaxPriceList(Pageable var);
}

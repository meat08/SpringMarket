package ru.geekbrains.springmarket.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springmarket.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Float minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(Float maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> nameLike(String namePart) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), String.format("%%%s%%", namePart.toLowerCase()));
    }
}

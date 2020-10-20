package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.entities.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private Float pricePerProduct;
    private Float price;

    public OrderItemDto(OrderItem o) {
        this.productId = o.getProduct().getId();
        this.productTitle = o.getProduct().getTitle();
        this.quantity = o.getQuantity();
        this.pricePerProduct = o.getPricePerProduct();
        this.price = o.getPrice();
    }
}

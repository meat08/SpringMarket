package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private Float price;

    public CartDto(Cart cart) {
        this.price = cart.getPrice();
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}

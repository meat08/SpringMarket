package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;


@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    private Float price;
    private String recipientName;
    private Long recipientPhone;
    private String recipientAddress;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.user = order.getUser();
        this.price = order.getPrice();
        this.recipientName = order.getRecipientName();
        this.recipientPhone = order.getRecipientPhone();
        this.recipientAddress = order.getRecipientAddress();
    }
}

package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.entities.Order;
import ru.geekbrains.springmarket.entities.User;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String user;
    private Float price;
    private String recipientName;
    private Long recipientPhone;
    private String recipientAddress;
    private List<OrderItemDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.user = order.getUser().getUsername();
        this.price = order.getPrice();
        this.recipientName = order.getRecipientName();
        this.recipientPhone = order.getRecipientPhone();
        this.recipientAddress = order.getRecipientAddress();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}

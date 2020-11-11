package ru.geekbrains.springmarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) //TODO eager временно, т.к. soap без авторизации и нет "сессии"
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price")
    private Float price;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_phone")
    private Long recipientPhone;

    @Column(name = "recipient_address")
    private String recipientAddress;

    public Order(List<OrderItem> items, User user, Float price, String recipientName, Long recipientPhone, String recipientAddress) {
        this.items = items;
        this.user = user;
        this.price = price;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.recipientAddress = recipientAddress;
        items.forEach(oi -> oi.setOrder(this));
    }
}

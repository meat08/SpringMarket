package ru.geekbrains.springmarket.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Integer birthday;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "address")
    private String address;
}
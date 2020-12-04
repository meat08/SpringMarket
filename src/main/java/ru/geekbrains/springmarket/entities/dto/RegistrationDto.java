package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import ru.geekbrains.springmarket.entities.Profile;


@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private Integer birthday;
    private Long phoneNumber;
    private String address;
}
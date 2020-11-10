package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.entities.Profile;


@Data
@NoArgsConstructor
public class ProfileDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer birthday;
    private Long phoneNumber;
    private String address;

    public ProfileDto(Profile profile) {
        this.username = profile.getUser().getUsername();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.email = profile.getEmail();
        this.birthday = profile.getBirthday();
        this.phoneNumber = profile.getPhoneNumber();
        this.address = profile.getAddress();
    }
}
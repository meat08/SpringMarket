package ru.geekbrains.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Profile;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.RegistrationDto;
import ru.geekbrains.springmarket.services.ProfileService;
import ru.geekbrains.springmarket.services.UserService;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ProfileService profileService;
    private final PasswordEncoder encoder;

    @PutMapping
    public void registration(@RequestBody RegistrationDto regData) {
        User user = new User(regData.getUsername(), regData.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        Profile profile = new Profile(user, regData.getFirstName(), regData.getLastName(),
                regData.getEmail(), regData.getBirthday(), regData.getPhoneNumber(), regData.getAddress());
        userService.saveUser(user);
        profileService.save(profile);
    }
}

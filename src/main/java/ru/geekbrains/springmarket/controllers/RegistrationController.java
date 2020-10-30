package ru.geekbrains.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.services.UserService;

@RestController
@RequestMapping("/registration") //сделал без /api/v1 чтобы не менять секьюрити
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @PostMapping
    public void registration(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
    }
}

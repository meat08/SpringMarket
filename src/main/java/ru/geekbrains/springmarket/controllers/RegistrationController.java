package ru.geekbrains.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Profile;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.RegistrationDto;
import ru.geekbrains.springmarket.exceptions.MarketError;
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
    public ResponseEntity<?> registration(@RequestBody RegistrationDto regData) {
        if (userService.findByUsername(regData.getUsername()).isPresent()) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Username already exist"), HttpStatus.BAD_REQUEST);
        }
        if (!regData.getPassword().equals(regData.getConfirmPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Password mismatch"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(regData.getUsername(), regData.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        Profile profile = new Profile(user, regData.getFirstName(), regData.getLastName(),
                regData.getEmail(), regData.getBirthday(), regData.getPhoneNumber(), regData.getAddress());
        userService.saveUser(user);
        profileService.save(profile);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

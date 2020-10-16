package ru.geekbrains.springmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.services.UserService;

import java.util.Map;


@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String userRegistration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
    //Прикрутил простенькую регистрацию без валидации
    //для валидации как я понимаю уже DTO нужны
    @PostMapping("/save_user")
    public String userSave(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }
}

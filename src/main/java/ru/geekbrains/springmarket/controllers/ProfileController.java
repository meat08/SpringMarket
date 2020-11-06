package ru.geekbrains.springmarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Profile;
import ru.geekbrains.springmarket.entities.dto.ProfileDto;
import ru.geekbrains.springmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.springmarket.services.ProfileService;
import ru.geekbrains.springmarket.services.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ProfileDto getProfileByUser(Principal principal) {
        return profileService.findByUsername(principal.getName()).map(ProfileDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for username: " + principal.getName()));
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(Principal principal, @RequestParam Map<String, String> params) {
        if (!passwordEncoder.matches(params.get("password"), userService.findByUsername(principal.getName()).getPassword())) {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED); //костыль, т.к. index.js перехватывает 401 и 403
        }
        Profile profile = profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for username: " + principal.getName()));
        profile.setFirstName(params.get("firstName"));
        profile.setLastName(params.get("lastName"));
        profile.setEmail(params.get("email"));
        profile.setBirthday(Integer.valueOf(params.get("birthday")));
        profile.setPhoneNumber(Long.valueOf(params.get("phoneNumber")));
        profile.setAddress(params.get("address"));
        profileService.save(profile);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

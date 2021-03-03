package ru.geekbrains.springmarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springmarket.entities.Profile;
import ru.geekbrains.springmarket.entities.User;
import ru.geekbrains.springmarket.entities.dto.ProfileDto;
import ru.geekbrains.springmarket.exceptions.MarketError;
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
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public ProfileDto getProfileByUser(Principal principal) {
        return profileService.findByUsername(principal.getName()).map(ProfileDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for username: " + principal.getName()));
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(Principal principal, @RequestBody ProfileDto profileDto, @RequestParam Map<String, String> params) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User '%s' not found", principal.getName())));
        if (!passwordEncoder.matches(params.get("password"), user.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Wrong password for user " + user.getUsername()), HttpStatus.UNAUTHORIZED);
       }
        Profile profile = profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for username: " + principal.getName()));
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setEmail(profileDto.getEmail());
        profile.setBirthday(profileDto.getBirthday());
        profile.setPhoneNumber(profileDto.getPhoneNumber());
        profile.setAddress(profileDto.getAddress());
        profileService.save(profile);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

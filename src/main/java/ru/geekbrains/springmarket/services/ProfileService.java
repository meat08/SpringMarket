package ru.geekbrains.springmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springmarket.entities.Profile;
import ru.geekbrains.springmarket.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository profileRepository;

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findByUsername(String username) {
        return profileRepository.findProfileByUsername(username);
    }

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

}

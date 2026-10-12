package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Entity.User;
import com.example.Backend.Repository.FitnessProfileRepository;
import com.example.Backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessProfileService {

    private final FitnessProfileRepository fitnessProfileRepository;
    private final UserRepository userRepository;

    public FitnessProfileService(
            FitnessProfileRepository fitnessProfileRepository,
            UserRepository userRepository) {

        this.fitnessProfileRepository = fitnessProfileRepository;
        this.userRepository = userRepository;
    }

    // Create Fitness Profile for User
    public FitnessProfile createProfile(
            FitnessProfile profile, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        profile.setUser(user);

        return fitnessProfileRepository.save(profile);
    }

    // Get All Profiles
    public List<FitnessProfile> getAllProfiles() {
        return fitnessProfileRepository.findAll();
    }

    // Get Profile By ID
    public Optional<FitnessProfile> getProfileById(Long id) {
        return fitnessProfileRepository.findById(id);
    }

    // Update Profile
    public FitnessProfile updateProfile(
            Long id, FitnessProfile profile) {

        FitnessProfile existingProfile =
                fitnessProfileRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Fitness profile not found"));

        existingProfile.setAge(profile.getAge());
        existingProfile.setGender(profile.getGender());
        existingProfile.setHeight(profile.getHeight());
        existingProfile.setWeight(profile.getWeight());
        existingProfile.setGoal(profile.getGoal());
        existingProfile.setActivityLevel(profile.getActivityLevel());
        existingProfile.setFitnessLevel(profile.getFitnessLevel());

        return fitnessProfileRepository.save(existingProfile);
    }

    // Delete Profile
    public void deleteProfile(Long id) {
        fitnessProfileRepository.deleteById(id);
    }

public Optional<FitnessProfile> getProfileByUserId(Long userId) {
    return fitnessProfileRepository.findFirstByUserId(userId);
}

}
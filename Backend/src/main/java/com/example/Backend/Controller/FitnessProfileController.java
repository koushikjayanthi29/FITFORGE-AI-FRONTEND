package com.example.Backend.Controller;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Service.FitnessProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fitness")
public class FitnessProfileController {

    private final FitnessProfileService fitnessProfileService;

    public FitnessProfileController(
            FitnessProfileService fitnessProfileService) {

        this.fitnessProfileService = fitnessProfileService;
    }

    // Create Fitness Profile
    @PostMapping("/profile/{userId}")
    public ResponseEntity<FitnessProfile> createProfile(
            @PathVariable Long userId,
            @RequestBody FitnessProfile profile) {

        return ResponseEntity.ok(
                fitnessProfileService.createProfile(profile, userId)
        );
    }

    // Get All Profiles
    @GetMapping("/profiles")
    public ResponseEntity<List<FitnessProfile>> getAllProfiles() {

        return ResponseEntity.ok(
                fitnessProfileService.getAllProfiles()
        );
    }

    // Get Profile By ID
    @GetMapping("/profile/{id}")
    public ResponseEntity<FitnessProfile> getProfileById(
            @PathVariable Long id) {

        return fitnessProfileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Profile
    @PutMapping("/profile/{id}")
    public ResponseEntity<FitnessProfile> updateProfile(
            @PathVariable Long id,
            @RequestBody FitnessProfile profile) {

        return ResponseEntity.ok(
                fitnessProfileService.updateProfile(id, profile)
        );
    }

    // Delete Profile
    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable Long id) {

        fitnessProfileService.deleteProfile(id);

        return ResponseEntity.ok(
                "Fitness profile deleted successfully"
        );
    }
}

package com.example.Backend.Controller;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Service.BmiService;
import com.example.Backend.Service.FitnessProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bmi")
public class BmiController {

    private final BmiService bmiService;
    private final FitnessProfileService fitnessProfileService;

    public BmiController(
            BmiService bmiService,
            FitnessProfileService fitnessProfileService) {

        this.bmiService = bmiService;
        this.fitnessProfileService = fitnessProfileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<?> calculateBmi(
            @PathVariable Long profileId) {

        FitnessProfile profile =
                fitnessProfileService.getProfileById(profileId)
                        .orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        double bmi = bmiService.calculateBmi(profile);

        String category = bmiService.getBmiCategory(bmi);

        Map<String, Object> response = new HashMap<>();

        response.put("profileId", profileId);
        response.put("height", profile.getHeight());
        response.put("weight", profile.getWeight());
        response.put("bmi", Math.round(bmi * 100.0) / 100.0);
        response.put("category", category);

        return ResponseEntity.ok(response);
    }
}
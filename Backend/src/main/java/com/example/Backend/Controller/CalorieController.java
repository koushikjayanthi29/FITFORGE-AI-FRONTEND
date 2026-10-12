package com.example.Backend.Controller;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Service.CalorieService;
import com.example.Backend.Service.FitnessProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calories")
public class CalorieController {

    private final CalorieService calorieService;
    private final FitnessProfileService fitnessProfileService;

    public CalorieController(
            CalorieService calorieService,
            FitnessProfileService fitnessProfileService) {

        this.calorieService = calorieService;
        this.fitnessProfileService = fitnessProfileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<?> calculateCalories(
            @PathVariable Long profileId) {

        FitnessProfile profile =
                fitnessProfileService.getProfileById(profileId)
                        .orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        double bmr = calorieService.calculateBmr(profile);

        double tdee =
                calorieService.calculateTdee(profile, bmr);
        double targetCalories =
                calorieService.calculateTargetCalories(profile, tdee);

        Map<String, Object> response = new HashMap<>();

        response.put("profileId", profileId);
        response.put("bmr",
                Math.round(bmr * 100.0) / 100.0);

        response.put("tdee",
                Math.round(tdee * 100.0) / 100.0);
        response.put("targetCalories",
                Math.round(targetCalories * 100.0) / 100.0);

        response.put("goal", profile.getGoal());
        response.put("activityLevel",
                profile.getActivityLevel());

        return ResponseEntity.ok(response);
    }
}
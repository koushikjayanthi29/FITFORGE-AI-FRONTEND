package com.example.Backend.Controller;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Service.FitnessProfileService;
import com.example.Backend.Service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final FitnessProfileService fitnessProfileService;
    private final RecommendationService recommendationService;

    public RecommendationController(
            FitnessProfileService fitnessProfileService,
            RecommendationService recommendationService) {
        this.fitnessProfileService = fitnessProfileService;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<?> getRecommendations(
            @PathVariable Long profileId) {

        FitnessProfile profile =
                fitnessProfileService.getProfileById(profileId)
                        .orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> recommendations =
                recommendationService.generateRecommendations(profile);

        return ResponseEntity.ok(recommendations);
    }
}
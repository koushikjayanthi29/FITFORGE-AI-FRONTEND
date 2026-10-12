package com.example.Backend.Controller;

import com.example.Backend.Service.NutritionRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/nutrition-recommendations")
public class NutritionRecommendationController {

    private final NutritionRecommendationService nutritionRecommendationService;

    public NutritionRecommendationController(
            NutritionRecommendationService nutritionRecommendationService) {
        this.nutritionRecommendationService = nutritionRecommendationService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getRecommendedNutrition(
            @PathVariable Long profileId) {

        Map<String, Object> recommendations =
                nutritionRecommendationService
                        .getRecommendedNutrition(profileId);

        return ResponseEntity.ok(recommendations);
    }
}
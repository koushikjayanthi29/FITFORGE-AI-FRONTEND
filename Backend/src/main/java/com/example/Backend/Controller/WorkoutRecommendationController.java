package com.example.Backend.Controller;

import com.example.Backend.Service.WorkoutRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workout-recommendations")
public class WorkoutRecommendationController {

    private final WorkoutRecommendationService workoutRecommendationService;

    public WorkoutRecommendationController(
            WorkoutRecommendationService workoutRecommendationService) {
        this.workoutRecommendationService = workoutRecommendationService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getRecommendedWorkouts(
            @PathVariable Long profileId) {

        Map<String, Object> recommendations =
                workoutRecommendationService.getRecommendedWorkouts(profileId);

        return ResponseEntity.ok(recommendations);
    }
}
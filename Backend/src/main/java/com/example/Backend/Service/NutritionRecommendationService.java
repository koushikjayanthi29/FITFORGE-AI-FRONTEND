package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Entity.NutritionPlan;
import com.example.Backend.Repository.NutritionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutritionRecommendationService {

    private final NutritionPlanRepository nutritionPlanRepository;
    private final FitnessProfileService fitnessProfileService;

    public NutritionRecommendationService(
            NutritionPlanRepository nutritionPlanRepository,
            FitnessProfileService fitnessProfileService) {

        this.nutritionPlanRepository = nutritionPlanRepository;
        this.fitnessProfileService = fitnessProfileService;
    }

    public Map<String, Object> getRecommendedNutrition(Long profileId) {

        FitnessProfile profile = fitnessProfileService
                .getProfileById(profileId)
                .orElseThrow(() ->
                        new RuntimeException("Fitness profile not found"));

        String goal = profile.getGoal();

        List<NutritionPlan> nutritionPlans =
                nutritionPlanRepository.findByGoalIgnoreCase(goal);

        Map<String, Object> response = new HashMap<>();

        response.put("profileId", profileId);
        response.put("goal", goal);
        response.put("totalPlans", nutritionPlans.size());
        response.put("nutritionPlans", nutritionPlans);

        return response;
    }
}
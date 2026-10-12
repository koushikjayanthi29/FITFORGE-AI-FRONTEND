package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    private final BmiService bmiService;

    public RecommendationService(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    public Map<String, Object> generateRecommendations(FitnessProfile profile) {

        double bmi = bmiService.calculateBmi(profile);
        String bmiCategory = bmiService.getBmiCategory(bmi);

        List<String> recommendations = new ArrayList<>();

        // BMI-based recommendation
        if (bmi < 18.5) {
            recommendations.add(
                "Focus on healthy weight gain with a calorie-rich and protein-rich diet."
            );
        } else if (bmi < 25) {
            recommendations.add(
                "Your BMI is in the normal range. Focus on maintaining a balanced lifestyle."
            );
        } else if (bmi < 30) {
            recommendations.add(
                "Focus on gradual weight loss through regular exercise and a controlled-calorie diet."
            );
        } else {
            recommendations.add(
                "Focus on gradual weight reduction with regular exercise and a balanced diet."
            );
        }

        // Goal-based recommendation
        if (profile.getGoal().equalsIgnoreCase("Weight Loss")) {
            recommendations.add(
                "Maintain a moderate calorie deficit and include regular cardio and strength training."
            );
        } else if (profile.getGoal().equalsIgnoreCase("Weight Gain")) {
            recommendations.add(
                "Maintain a calorie surplus and focus on protein-rich foods and strength training."
            );
        } else if (profile.getGoal().equalsIgnoreCase("Muscle Gain")) {
            recommendations.add(
                "Prioritize strength training, sufficient protein intake, and adequate recovery."
            );
        } else {
            recommendations.add(
                "Maintain a balanced workout routine and a nutritious diet."
            );
        }

        // Activity-level recommendation
        if (profile.getActivityLevel().equalsIgnoreCase("Sedentary")) {
            recommendations.add(
                "Start with light physical activity and gradually increase your daily movement."
            );
        } else if (profile.getActivityLevel().equalsIgnoreCase("Light")) {
            recommendations.add(
                "Try to increase your weekly physical activity gradually."
            );
        } else if (profile.getActivityLevel().equalsIgnoreCase("Moderate")) {
            recommendations.add(
                "Continue your moderate activity level and maintain consistency."
            );
        } else {
            recommendations.add(
                "Maintain your active lifestyle while allowing sufficient recovery."
            );
        }

        // Fitness-level recommendation
        if (profile.getFitnessLevel().equalsIgnoreCase("Beginner")) {
            recommendations.add(
                "Start with basic exercises and focus on proper exercise technique."
            );
        } else if (profile.getFitnessLevel().equalsIgnoreCase("Intermediate")) {
            recommendations.add(
                "Gradually increase workout intensity and resistance as your fitness improves."
            );
        } else if (profile.getFitnessLevel().equalsIgnoreCase("Advanced")) {
            recommendations.add(
                "Use progressive overload and structured training to continue improving performance."
            );
        }

        Map<String, Object> response = new HashMap<>();

        response.put("profileId", profile.getId());
        response.put("bmi", Math.round(bmi * 100.0) / 100.0);
        response.put("bmiCategory", bmiCategory);
        response.put("goal", profile.getGoal());
        response.put("activityLevel", profile.getActivityLevel());
        response.put("fitnessLevel", profile.getFitnessLevel());
        response.put("recommendations", recommendations);

        return response;
    }
}
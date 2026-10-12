package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import org.springframework.stereotype.Service;

@Service
public class CalorieService {

    public double calculateBmr(FitnessProfile profile) {

        double weight = profile.getWeight();
        double height = profile.getHeight();
        int age = profile.getAge();

        if (profile.getGender().equalsIgnoreCase("Male")) {

            return (10 * weight) +
                   (6.25 * height) -
                   (5 * age) +
                   5;

        } else {

            return (10 * weight) +
                   (6.25 * height) -
                   (5 * age) -
                   161;
        }
    }

    public double calculateTdee(
            FitnessProfile profile,
            double bmr) {

        String activity = profile.getActivityLevel();

        double multiplier;

        switch (activity.toLowerCase()) {

            case "sedentary":
                multiplier = 1.2;
                break;

            case "light":
                multiplier = 1.375;
                break;

            case "moderate":
                multiplier = 1.55;
                break;

            case "active":
                multiplier = 1.725;
                break;

            case "very active":
                multiplier = 1.9;
                break;

            default:
                multiplier = 1.2;
        }

        return bmr * multiplier;
    }
    public double calculateTargetCalories(
            FitnessProfile profile,
            double tdee) {

        String goal = profile.getGoal();

        if (goal.equalsIgnoreCase("Weight Loss")) {

            return tdee - 500;

        } else if (goal.equalsIgnoreCase("Weight Gain")) {

            return tdee + 300;

        } else if (goal.equalsIgnoreCase("Muscle Gain")) {

            return tdee + 200;

        } else {

            return tdee;
        }
    }
}
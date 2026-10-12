package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import org.springframework.stereotype.Service;

@Service
public class BmiService {

    public double calculateBmi(FitnessProfile profile) {

        double heightInMeters = profile.getHeight() / 100;

        return profile.getWeight() /
                (heightInMeters * heightInMeters);
    }

    public String getBmiCategory(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal Weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
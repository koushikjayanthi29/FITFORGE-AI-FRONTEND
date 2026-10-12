package com.example.Backend.Service;

import com.example.Backend.Entity.NutritionPlan;
import com.example.Backend.Repository.NutritionPlanRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionPlanService {

    private final NutritionPlanRepository nutritionPlanRepository;

    public NutritionPlanService(
            NutritionPlanRepository nutritionPlanRepository) {

        this.nutritionPlanRepository = nutritionPlanRepository;
    }

    public NutritionPlan createPlan(NutritionPlan plan) {
        return nutritionPlanRepository.save(plan);
    }

    public List<NutritionPlan> getAllPlans() {
        return nutritionPlanRepository.findAll();
    }

    public Optional<NutritionPlan> getPlanById(Long id) {
        return nutritionPlanRepository.findById(id);
    }

    public List<NutritionPlan> getPlansByGoal(String goal) {
        return nutritionPlanRepository.findByGoalIgnoreCase(goal);
    }

    public NutritionPlan updatePlan(
            Long id,
            NutritionPlan plan) {

        NutritionPlan existingPlan =
                nutritionPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Nutrition plan not found"));

        existingPlan.setMealName(plan.getMealName());
        existingPlan.setFoodItems(plan.getFoodItems());
        existingPlan.setCalories(plan.getCalories());
        existingPlan.setProtein(plan.getProtein());
        existingPlan.setCarbohydrates(plan.getCarbohydrates());
        existingPlan.setFats(plan.getFats());
        existingPlan.setGoal(plan.getGoal());

        return nutritionPlanRepository.save(existingPlan);
    }

    public void deletePlan(Long id) {
        nutritionPlanRepository.deleteById(id);
    }
}
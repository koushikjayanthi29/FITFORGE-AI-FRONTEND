package com.example.Backend.Controller;

import com.example.Backend.Entity.NutritionPlan;
import com.example.Backend.Service.NutritionPlanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;

    public NutritionPlanController(
            NutritionPlanService nutritionPlanService) {

        this.nutritionPlanService = nutritionPlanService;
    }

    @PostMapping
    public ResponseEntity<NutritionPlan> createPlan(
            @RequestBody NutritionPlan plan) {

        return ResponseEntity.ok(
                nutritionPlanService.createPlan(plan)
        );
    }

    @GetMapping
    public ResponseEntity<List<NutritionPlan>> getAllPlans() {

        return ResponseEntity.ok(
                nutritionPlanService.getAllPlans()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionPlan> getPlanById(
            @PathVariable Long id) {

        return nutritionPlanService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/goal/{goal}")
    public ResponseEntity<List<NutritionPlan>> getPlansByGoal(
            @PathVariable String goal) {

        return ResponseEntity.ok(
                nutritionPlanService.getPlansByGoal(goal)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutritionPlan> updatePlan(
            @PathVariable Long id,
            @RequestBody NutritionPlan plan) {

        return ResponseEntity.ok(
                nutritionPlanService.updatePlan(id, plan)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(
            @PathVariable Long id) {

        nutritionPlanService.deletePlan(id);

        return ResponseEntity.ok(
                "Nutrition plan deleted successfully"
        );
    }
}
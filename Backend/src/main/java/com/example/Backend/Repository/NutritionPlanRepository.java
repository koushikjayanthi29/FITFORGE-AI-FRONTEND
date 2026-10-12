package com.example.Backend.Repository;

import com.example.Backend.Entity.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutritionPlanRepository
        extends JpaRepository<NutritionPlan, Long> {

    List<NutritionPlan> findByGoalIgnoreCase(String goal);
}
package com.example.Backend.Service;

import com.example.Backend.Entity.FitnessProfile;
import com.example.Backend.Entity.Workout;
import com.example.Backend.Repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkoutRecommendationService {

    private final WorkoutRepository workoutRepository;
    private final FitnessProfileService fitnessProfileService;

    public WorkoutRecommendationService(
            WorkoutRepository workoutRepository,
            FitnessProfileService fitnessProfileService) {
        this.workoutRepository = workoutRepository;
        this.fitnessProfileService = fitnessProfileService;
    }

    public Map<String, Object> getRecommendedWorkouts(Long profileId) {

        FitnessProfile profile = fitnessProfileService
                .getProfileById(profileId)
                .orElseThrow(() ->
                        new RuntimeException("Fitness profile not found"));

        String fitnessLevel = profile.getFitnessLevel();

        List<Workout> workouts =
                workoutRepository.findByDifficultyIgnoreCase(fitnessLevel);

        Map<String, Object> response = new HashMap<>();

        response.put("profileId", profileId);
        response.put("goal", profile.getGoal());
        response.put("fitnessLevel", fitnessLevel);
        response.put("totalWorkouts", workouts.size());
        response.put("workouts", workouts);

        return response;
    }
}
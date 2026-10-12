package com.example.Backend.Controller;

import com.example.Backend.Entity.Workout;
import com.example.Backend.Service.WorkoutService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(
            @RequestBody Workout workout) {

        return ResponseEntity.ok(
                workoutService.createWorkout(workout)
        );
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Workout>> getWorkoutsByDifficulty(
            @PathVariable String difficulty) {

        return ResponseEntity.ok(
                workoutService.getWorkoutsByDifficulty(difficulty)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(
            @PathVariable Long id) {

        return workoutService.getWorkoutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(
            @PathVariable Long id,
            @RequestBody Workout workout) {

        return ResponseEntity.ok(
                workoutService.updateWorkout(id, workout)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(
            @PathVariable Long id) {

        workoutService.deleteWorkout(id);

        return ResponseEntity.ok(
                "Workout deleted successfully"
        );
    }
}
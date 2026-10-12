package com.example.Backend.Service;

import com.example.Backend.Entity.Workout;
import com.example.Backend.Repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
    public List<Workout> getWorkoutsByDifficulty(String difficulty) {
        return workoutRepository.findByDifficultyIgnoreCase(difficulty);
    }
    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    public Workout updateWorkout(Long id, Workout workout) {

        Workout existingWorkout =
                workoutRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Workout not found"));

        existingWorkout.setWorkoutName(
                workout.getWorkoutName());

        existingWorkout.setExerciseName(
                workout.getExerciseName());

        existingWorkout.setTargetMuscle(
                workout.getTargetMuscle());

        existingWorkout.setSets(
                workout.getSets());

        existingWorkout.setRepetitions(
                workout.getRepetitions());

        existingWorkout.setDuration(
                workout.getDuration());

        existingWorkout.setDifficulty(
                workout.getDifficulty());

        return workoutRepository.save(existingWorkout);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}
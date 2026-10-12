package com.example.Backend.Repository;

import com.example.Backend.Entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByDifficultyIgnoreCase(String difficulty);
}
package com.example.Backend.Repository;

import com.example.Backend.Entity.TrainerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerAssignmentRepository
        extends JpaRepository<TrainerAssignment, Long> {

    List<TrainerAssignment> findByUserId(Long userId);

    List<TrainerAssignment> findByTrainerId(Long trainerId);

    List<TrainerAssignment> findByStatusIgnoreCase(String status);
}
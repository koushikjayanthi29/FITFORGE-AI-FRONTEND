package com.example.Backend.Service;

import com.example.Backend.Entity.Trainer;
import com.example.Backend.Entity.TrainerAssignment;
import com.example.Backend.Entity.User;
import com.example.Backend.Repository.TrainerAssignmentRepository;
import com.example.Backend.Repository.TrainerRepository;
import com.example.Backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerAssignmentService {

    private final TrainerAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;

    public TrainerAssignmentService(
            TrainerAssignmentRepository assignmentRepository,
            UserRepository userRepository,
            TrainerRepository trainerRepository) {

        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
    }

    public TrainerAssignment assignTrainer(Long userId, Long trainerId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() ->
                        new RuntimeException("Trainer not found"));

        TrainerAssignment assignment = new TrainerAssignment();

        assignment.setUser(user);
        assignment.setTrainer(trainer);
        assignment.setAssignmentDate(LocalDate.now());
        assignment.setStatus("ACTIVE");

        return assignmentRepository.save(assignment);
    }

    public List<TrainerAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<TrainerAssignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public List<TrainerAssignment> getAssignmentsByUser(Long userId) {
        return assignmentRepository.findByUserId(userId);
    }

    public List<TrainerAssignment> getAssignmentsByTrainer(Long trainerId) {
        return assignmentRepository.findByTrainerId(trainerId);
    }

    public List<TrainerAssignment> getAssignmentsByStatus(String status) {
        return assignmentRepository.findByStatusIgnoreCase(status);
    }

    public TrainerAssignment updateAssignment(
            Long id,
            String status) {

        TrainerAssignment assignment =
                assignmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Assignment not found"));

        assignment.setStatus(status);

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}
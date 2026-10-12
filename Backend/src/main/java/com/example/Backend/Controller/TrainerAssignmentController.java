package com.example.Backend.Controller;

import com.example.Backend.Entity.TrainerAssignment;
import com.example.Backend.Service.TrainerAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer-assignments")
public class TrainerAssignmentController {

    private final TrainerAssignmentService assignmentService;

    public TrainerAssignmentController(
            TrainerAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/assign/{userId}/{trainerId}")
    public ResponseEntity<TrainerAssignment> assignTrainer(
            @PathVariable Long userId,
            @PathVariable Long trainerId) {

        return ResponseEntity.ok(
                assignmentService.assignTrainer(userId, trainerId)
        );
    }

    @GetMapping
    public ResponseEntity<List<TrainerAssignment>> getAllAssignments() {

        return ResponseEntity.ok(
                assignmentService.getAllAssignments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerAssignment> getAssignmentById(
            @PathVariable Long id) {

        return assignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainerAssignment>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByUser(userId)
        );
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<TrainerAssignment>> getByTrainer(
            @PathVariable Long trainerId) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByTrainer(trainerId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TrainerAssignment>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByStatus(status)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerAssignment> updateAssignment(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                assignmentService.updateAssignment(id, status)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok(
                "Trainer assignment deleted successfully"
        );
    }
}
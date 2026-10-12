package com.example.Backend.Controller;

import com.example.Backend.Entity.Trainer;
import com.example.Backend.Service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(
            @RequestBody Trainer trainer) {

        return ResponseEntity.ok(
                trainerService.createTrainer(trainer)
        );
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {

        return ResponseEntity.ok(
                trainerService.getAllTrainers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(
            @PathVariable Long id) {

        return trainerService.getTrainerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Trainer>> getBySpecialization(
            @PathVariable String specialization) {

        return ResponseEntity.ok(
                trainerService.getTrainersBySpecialization(
                        specialization
                )
        );
    }

    @GetMapping("/availability/{availability}")
    public ResponseEntity<List<Trainer>> getByAvailability(
            @PathVariable String availability) {

        return ResponseEntity.ok(
                trainerService.getTrainersByAvailability(
                        availability
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(
            @PathVariable Long id,
            @RequestBody Trainer trainer) {

        return ResponseEntity.ok(
                trainerService.updateTrainer(id, trainer)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(
            @PathVariable Long id) {

        trainerService.deleteTrainer(id);

        return ResponseEntity.ok(
                "Trainer deleted successfully"
        );
    }
}
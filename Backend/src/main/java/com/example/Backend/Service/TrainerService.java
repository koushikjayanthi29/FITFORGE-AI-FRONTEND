package com.example.Backend.Service;

import com.example.Backend.Entity.Trainer;
import com.example.Backend.Repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    public List<Trainer> getTrainersBySpecialization(String specialization) {
        return trainerRepository.findBySpecializationIgnoreCase(specialization);
    }

    public List<Trainer> getTrainersByAvailability(String availability) {
        return trainerRepository.findByAvailabilityIgnoreCase(availability);
    }

    public Trainer updateTrainer(Long id, Trainer trainer) {

        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Trainer not found"));

        existingTrainer.setName(trainer.getName());
        existingTrainer.setEmail(trainer.getEmail());
        existingTrainer.setSpecialization(trainer.getSpecialization());
        existingTrainer.setExperience(trainer.getExperience());
        existingTrainer.setCertification(trainer.getCertification());
        existingTrainer.setAvailability(trainer.getAvailability());

        return trainerRepository.save(existingTrainer);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
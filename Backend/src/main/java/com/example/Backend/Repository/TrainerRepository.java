package com.example.Backend.Repository;

import com.example.Backend.Entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findBySpecializationIgnoreCase(String specialization);

    List<Trainer> findByAvailabilityIgnoreCase(String availability);
}
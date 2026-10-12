package com.example.Backend.Service;

import com.example.Backend.Entity.Progress;
import com.example.Backend.Entity.User;
import com.example.Backend.Repository.ProgressRepository;
import com.example.Backend.Repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    public ProgressService(
            ProgressRepository progressRepository,
            UserRepository userRepository) {

        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
    }

    public Progress createProgress(
            Progress progress,
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        progress.setUser(user);

        return progressRepository.save(progress);
    }

    public List<Progress> getUserProgress(Long userId) {

        return progressRepository
                .findByUserIdOrderByDateDesc(userId);
    }

    public Optional<Progress> getProgressById(Long id) {

        return progressRepository.findById(id);
    }

    public Progress updateProgress(
            Long id,
            Progress progress) {

        Progress existingProgress =
                progressRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Progress not found"));

        existingProgress.setWeight(
                progress.getWeight());

        existingProgress.setBodyFat(
                progress.getBodyFat());

        existingProgress.setDate(
                progress.getDate());

        existingProgress.setNotes(
                progress.getNotes());

        return progressRepository.save(existingProgress);
    }

    public void deleteProgress(Long id) {

        progressRepository.deleteById(id);
    }
}
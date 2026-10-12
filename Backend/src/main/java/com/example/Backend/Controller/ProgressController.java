package com.example.Backend.Controller;

import com.example.Backend.Entity.Progress;
import com.example.Backend.Service.ProgressService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Progress> createProgress(
            @PathVariable Long userId,
            @RequestBody Progress progress) {

        return ResponseEntity.ok(
                progressService.createProgress(progress, userId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getUserProgress(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                progressService.getUserProgress(userId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgressById(
            @PathVariable Long id) {

        return progressService.getProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Progress> updateProgress(
            @PathVariable Long id,
            @RequestBody Progress progress) {

        return ResponseEntity.ok(
                progressService.updateProgress(id, progress)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgress(
            @PathVariable Long id) {

        progressService.deleteProgress(id);

        return ResponseEntity.ok(
                "Progress deleted successfully"
        );
    }
}
package com.example.Backend.Controller;

import com.example.Backend.Service.ProgressAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/progress")
public class ProgressAnalyticsController {

    private final ProgressAnalyticsService progressAnalyticsService;

    public ProgressAnalyticsController(
            ProgressAnalyticsService progressAnalyticsService) {
        this.progressAnalyticsService = progressAnalyticsService;
    }

    @GetMapping("/analytics/{userId}")
    public ResponseEntity<Map<String, Object>> getProgressAnalytics(
            @PathVariable Long userId) {

        Map<String, Object> analytics =
                progressAnalyticsService.getProgressAnalytics(userId);

        return ResponseEntity.ok(analytics);
    }
}
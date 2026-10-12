package com.example.Backend.Service;

import com.example.Backend.Entity.Progress;
import com.example.Backend.Repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgressAnalyticsService {

    private final ProgressRepository progressRepository;

    public ProgressAnalyticsService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public Map<String, Object> getProgressAnalytics(Long userId) {

        List<Progress> progressList =
                progressRepository.findByUserIdOrderByDateDesc(userId);

        Map<String, Object> response = new HashMap<>();

        if (progressList.isEmpty()) {
            response.put("message", "No progress data found");
            return response;
        }

        // Latest progress
        Progress latest = progressList.get(0);

        // Oldest progress
        Progress oldest = progressList.get(progressList.size() - 1);

        double startingWeight = oldest.getWeight();
        double currentWeight = latest.getWeight();

        double weightChange = currentWeight - startingWeight;

        double startingBodyFat =
                oldest.getBodyFat() != null ? oldest.getBodyFat() : 0.0;

        double currentBodyFat =
                latest.getBodyFat() != null ? latest.getBodyFat() : 0.0;

        double bodyFatChange = currentBodyFat - startingBodyFat;

        String status;

        if (weightChange < 0) {
            status = "Weight Loss Progress";
        } else if (weightChange > 0) {
            status = "Weight Gain Progress";
        } else {
            status = "Weight Maintained";
        }

        response.put("userId", userId);
        response.put("startingWeight", startingWeight);
        response.put("currentWeight", currentWeight);
        response.put("weightChange", weightChange);
        response.put("startingBodyFat", startingBodyFat);
        response.put("currentBodyFat", currentBodyFat);
        response.put("bodyFatChange", bodyFatChange);
        response.put("status", status);
        response.put("totalRecords", progressList.size());

        return response;
    }
}
package com.example.Backend.Repository;

import com.example.Backend.Entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByUserIdOrderByDateDesc(Long userId);
}
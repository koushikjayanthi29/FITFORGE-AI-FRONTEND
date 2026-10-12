
package com.example.Backend.Repository;

import com.example.Backend.Entity.FitnessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FitnessProfileRepository extends JpaRepository<FitnessProfile, Long> {

    Optional<FitnessProfile> findFirstByUserId(Long userId);
}

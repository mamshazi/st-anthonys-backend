package com.stanthonys.backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequest, Long> {
    
}
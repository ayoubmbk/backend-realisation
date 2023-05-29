package com.example.demo.repo;

import com.example.demo.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepos extends JpaRepository<Phase, Long> {
}

package com.example.demo.repo;

import com.example.demo.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface
PhaseRepos extends JpaRepository<Phase, Long> {
    List<Phase> findByProjectProjetId(Long projectId);

}

package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetDto {
    private Long projectId;
    private String projectName;
    private int totalTasks;
    private int totalPhases;

}

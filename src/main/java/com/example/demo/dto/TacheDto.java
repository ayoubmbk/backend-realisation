package com.example.demo.dto;

import com.example.demo.enumeration.Niveau;
import com.example.demo.enumeration.Status;
import lombok.Data;

@Data
public class TacheDto {
    private Long idTache;
    private Status status;
    private Niveau nivTache;
    private String nomTache;
    private Long phaseId;
    private Long projetId;
}

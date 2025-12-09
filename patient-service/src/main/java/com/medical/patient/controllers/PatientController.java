package com.medical.patient.controllers;

import com.medical.entities.RendezVous;
import com.medical.patient.feign.RendezVousClient;
import com.medical.patient.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepo;
    private final RendezVousClient rdvClient;

    public PatientController(PatientRepository patientRepo, RendezVousClient rdvClient) {
        this.patientRepo = patientRepo;
        this.rdvClient = rdvClient;
    }

    @GetMapping("/{id}/rdvs")
    public List<RendezVous> getRendezVous(@PathVariable Long id) {
        return rdvClient.getRendezVousByPatient(id);
    }
}



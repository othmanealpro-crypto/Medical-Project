package com.medical.patient.controllers;

import com.medical.entities.Patient;
import com.medical.patient.repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepo;

    // Constructeur explicite pour Spring
    public PatientController(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return patientRepo.save(patient);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

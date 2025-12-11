package com.medical.rdv.controllers;

import com.medical.entities.RendezVous;
import com.medical.rdv.repositories.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rdv")
public class RendezVousController {

    private final RendezVousRepository rdvRepo;

    public RendezVousController(RendezVousRepository rdvRepo) {
        this.rdvRepo = rdvRepo;
    }

    @PostMapping
    public RendezVous create(@RequestBody RendezVous rdv) {
        return rdvRepo.save(rdv);
    }

    @GetMapping
    public List<RendezVous> getAll() {
        return rdvRepo.findAll();
    }

    @GetMapping("/patient/{id}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable Long id) {
        return rdvRepo.findByPatientId(id);
    }



}

package com.medical.rdv.controllers;

import com.medical.entities.Medecin;
import com.medical.rdv.repositories.MedecinRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecins")
public class MedecinController {

    private final MedecinRepository medecinRepo;

    // Constructeur explicite pour Spring
    public MedecinController(MedecinRepository medecinRepo) {
        this.medecinRepo = medecinRepo;
    }

    @PostMapping
    public Medecin create(@RequestBody Medecin medecin) {
        return medecinRepo.save(medecin);
    }

    @GetMapping
    public List<Medecin> getAll() {
        return medecinRepo.findAll();
    }
}

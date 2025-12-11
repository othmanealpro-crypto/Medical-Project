package com.medical.interfaceapp.Model;

import java.time.LocalDateTime;

public class RendezVous {
    private Long id;
    private Patient patient;
    private Medecin medecin;
    private LocalDateTime dateHeure;
    private String symptomes;

    public RendezVous() {}

    public RendezVous(Long id, Patient patient, Medecin medecin, LocalDateTime dateHeure, String symptomes) {
        this.id = id;
        this.patient = patient;
        this.medecin = medecin;
        this.dateHeure = dateHeure;
        this.symptomes = symptomes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public String getSymptomes() { return symptomes; }
    public void setSymptomes(String symptomes) { this.symptomes = symptomes; }
}

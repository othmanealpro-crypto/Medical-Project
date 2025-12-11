package com.medical.interfaceapp.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RendezVous {
    private Long id;
    private Patient patient;
    private Medecin medecin;
    private LocalDate dateRdv;  // maintenant LocalDateTime
    private String symptomes;

    public RendezVous() {}

    public RendezVous(Long id, Patient patient, Medecin medecin, LocalDate dateRdv, String symptomes) {
        this.id = id;
        this.patient = patient;
        this.medecin = medecin;
        this.dateRdv = dateRdv;
        this.symptomes = symptomes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public LocalDate getDateRdv() { return dateRdv; }
    public void setDateRdv(LocalDate dateRdv) { this.dateRdv = dateRdv; }

    public String getSymptomes() { return symptomes; }
    public void setSymptomes(String symptomes) { this.symptomes = symptomes; }
}

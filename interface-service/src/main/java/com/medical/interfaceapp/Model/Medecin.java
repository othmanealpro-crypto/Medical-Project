package com.medical.interfaceapp.Model;

import java.time.LocalDateTime;

public class Medecin {
    private Long id;
    private String nom;
    private String prenom;
    private String profession;

    public Medecin() {}

    public Medecin(Long id, String nom, String prenom, String profession) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
}

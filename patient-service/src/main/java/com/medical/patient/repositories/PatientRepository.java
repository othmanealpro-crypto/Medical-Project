package com.medical.patient.repositories;

import com.medical.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Exemple : chercher par nom
    // List<Patient> findByNom(String nom);
}

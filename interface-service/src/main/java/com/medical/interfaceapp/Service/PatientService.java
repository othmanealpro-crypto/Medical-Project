package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Service
public class PatientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String patientServiceUrl = "http://localhost:8081/patients";

    public List<Patient> getAllPatients() {
        try {
            ResponseEntity<List<Patient>> response = restTemplate.exchange(
                    patientServiceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Patient>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            System.err.println("Erreur lors de la récupération des patients : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Patient findById(Long id) {
        try {
            return restTemplate.getForObject(patientServiceUrl + "/" + id, Patient.class);
        } catch (RestClientException e) {
            System.err.println("Patient introuvable avec id " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Patient save(Patient patient) {
        try {
            return restTemplate.postForObject(patientServiceUrl, patient, Patient.class);
        } catch (RestClientException e) {
            System.err.println("Impossible de sauvegarder le patient : " + e.getMessage());
            return null;
        }
    }
}

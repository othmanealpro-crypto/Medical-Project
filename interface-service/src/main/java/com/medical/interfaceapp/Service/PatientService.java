package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class PatientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String patientServiceUrl = "http://localhost:8081/patients"; // URL de ton patient-service

    public List<Patient> getAllPatients() {
        ResponseEntity<List<Patient>> response = restTemplate.exchange(
                patientServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Patient>>() {}
        );
        return response.getBody();
    }
}

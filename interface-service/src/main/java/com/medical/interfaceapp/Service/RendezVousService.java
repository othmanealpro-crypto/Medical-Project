package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.RendezVous;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class RendezVousService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String rdvServiceUrl = "http://localhost:8082/rdv"; // URL de ton rdv-service

    public List<RendezVous> getAllRendezVous() {
        ResponseEntity<List<RendezVous>> response = restTemplate.exchange(
                rdvServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RendezVous>>() {}
        );
        return response.getBody();
    }
}

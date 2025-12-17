package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Medecin;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Service
public class MedecinService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String medecinServiceUrl = "http://localhost:8082/medecins";

    // Récupérer tous les médecins
    public List<Medecin> getAllMedecins() {
        try {
            ResponseEntity<List<Medecin>> response = restTemplate.exchange(
                    medecinServiceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Medecin>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            System.err.println("Erreur lors de la récupération des médecins : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Récupérer un médecin par ID
    public Medecin findById(Long id) {
        try {
            return restTemplate.getForObject(medecinServiceUrl + "/" + id, Medecin.class);
        } catch (RestClientException e) {
            System.err.println("Médecin introuvable avec id " + id + ": " + e.getMessage());
            return null;
        }
    }

    // Ajouter un nouveau médecin
    public Medecin save(Medecin medecin) {
        try {
            return restTemplate.postForObject(medecinServiceUrl, medecin, Medecin.class);
        } catch (RestClientException e) {
            System.err.println("Impossible de sauvegarder le médecin : " + e.getMessage());
            return null;
        }
    }
}

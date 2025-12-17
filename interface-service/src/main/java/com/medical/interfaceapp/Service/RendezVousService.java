package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Medecin;
import com.medical.interfaceapp.Model.RendezVous;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RendezVousService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String rdvServiceUrl = "http://localhost:8082"; // rdv-service URL

    /** Récupère un médecin via le microservice rdv-service */
    public Medecin getMedecinById(LocalDateTime medecinId) {
        String url = rdvServiceUrl + "/medecins/" + medecinId;
        try {
            Medecin medecin = restTemplate.getForObject(url, Medecin.class);
            if (medecin == null) {
                throw new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Médecin introuvable avec id " + medecinId
                );
            }
            return medecin;
        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Médecin introuvable avec id " + medecinId
            );
        }
    }




    /** Sauvegarde un rendez-vous via rdv-service */
    public RendezVous save(RendezVous rdv) {
        try {
            return restTemplate.postForObject(rdvServiceUrl + "/rdv", rdv, RendezVous.class);
        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Impossible de sauvegarder le rendez-vous : " + e.getMessage()
            );
        }
    }

    /** Récupère tous les rendez-vous */
    public List<RendezVous> getAllRendezVous() {
        try {
            ResponseEntity<List<RendezVous>> response = restTemplate.exchange(
                    rdvServiceUrl + "/rendezvous",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RendezVous>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }
}

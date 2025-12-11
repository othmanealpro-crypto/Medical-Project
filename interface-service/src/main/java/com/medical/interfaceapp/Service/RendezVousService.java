package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Medecin;
import com.medical.interfaceapp.Model.RendezVous;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RendezVousService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String rdvServiceUrl = "http://localhost:8082/rdv";

    public List<RendezVous> getAllRendezVous() {
        try {
            ResponseEntity<List<RendezVous>> response = restTemplate.exchange(
                    rdvServiceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RendezVous>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            System.err.println("Erreur lors de la récupération des rendez-vous : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Vérifie si le créneau est occupé
    public boolean existsByMedecinAndDateTime(Long medecinId, LocalDateTime dateTime) {
        return getAllRendezVous().stream()
                .anyMatch(r -> r.getMedecin() != null
                        && r.getMedecin().getId().equals(medecinId)
                        && r.getDateRdv() != null
                        && r.getDateRdv().equals(dateTime));
    }

    public boolean isCreneauLibre(Medecin medecin, LocalDateTime dateRdv) {
        return getAllRendezVous().stream()
                .noneMatch(r -> r.getMedecin() != null
                        && r.getMedecin().getId().equals(medecin.getId())
                        && r.getDateRdv() != null
                        && r.getDateRdv().equals(dateRdv));
    }

    public RendezVous save(RendezVous rdv) {
        try {
            // le rdv.dateRdv est déjà LocalDateTime avec date + heure
            return restTemplate.postForObject(rdvServiceUrl, rdv, RendezVous.class);
        } catch (RestClientException e) {
            System.err.println("Impossible de sauvegarder le rendez-vous : " + e.getMessage());
            return null;
        }
}
}

package com.medical.interfaceapp.Service;

import com.medical.interfaceapp.Model.Medecin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class MedecinService {

    // Pour l'instant, liste simulée. Plus tard tu peux appeler ton microservice medecin-service
    public List<Medecin> getAllMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        medecins.add(new Medecin(1L, "Dupont", "Jean", "Cardiologue"));
        medecins.add(new Medecin(2L, "Martin", "Claire", "Dermatologue"));
        return medecins;
    }
}

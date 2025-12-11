package com.medical.interfaceapp.Controller;

import com.medical.interfaceapp.Model.Patient;
import com.medical.interfaceapp.Model.Medecin;
import com.medical.interfaceapp.Model.RendezVous;
import com.medical.interfaceapp.Service.PatientService;
import com.medical.interfaceapp.Service.RendezVousService;
import com.medical.interfaceapp.Service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RendezVousService rendezVousService;

    @Autowired
    private MedecinService medecinService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Pas d'appel aux services pour isoler le problème
        return "dashboard";
    }

}

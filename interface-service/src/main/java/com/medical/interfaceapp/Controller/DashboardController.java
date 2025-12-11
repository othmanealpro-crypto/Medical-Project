package com.medical.interfaceapp.Controller;

import com.medical.interfaceapp.Model.Patient;
import com.medical.interfaceapp.Model.Medecin;
import com.medical.interfaceapp.Model.RendezVous;
import com.medical.interfaceapp.Service.PatientService;
import com.medical.interfaceapp.Service.RendezVousService;
import com.medical.interfaceapp.Service.MedecinService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DashboardController {

    private final PatientService patientService;
    private final MedecinService medecinService;
    private final RendezVousService rdvService;
    private final WebClient webClientPatients;
    private final WebClient webClientRdv;

    public DashboardController(PatientService patientService,
                               MedecinService medecinService,
                               RendezVousService rdvService,
                               WebClient.Builder webClientBuilder) {
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.rdvService = rdvService;
        this.webClientPatients = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.webClientRdv = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Patient> patients = webClientPatients.get()
                .uri("/patients")
                .retrieve()
                .bodyToFlux(Patient.class)
                .collectList()
                .block();

        List<RendezVous> rdvs = webClientRdv.get()
                .uri("/rdv")
                .retrieve()
                .bodyToFlux(RendezVous.class)
                .collectList()
                .block();

        List<Medecin> medecins = medecinService.getAllMedecins();

        model.addAttribute("patients", patients);
        model.addAttribute("rdvs", rdvs);
        model.addAttribute("medecins", medecins);
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("newRdv", new RendezVous());
        model.addAttribute("today", LocalDate.now());

        model.addAttribute("heures", List.of(
                "09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"
        ));

        return "dashboard";
    }

    @PostMapping("/dashboard/patients")
    public String addPatient(@RequestParam String nom,
                             @RequestParam String prenom,
                             @RequestParam String cin,
                             @RequestParam String telephone,
                             @RequestParam String email) {
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setCin(cin);
        patient.setTelephone(telephone);
        patient.setEmail(email);
        webClientPatients.post()
                .uri("/patients")
                .bodyValue(patient)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/medecins")
    public String addMedecin(@RequestParam String nom,
                             @RequestParam String specialite) {
        Medecin medecin = new Medecin();
        medecin.setNom(nom);
        medecin.setProfession(specialite);
        medecinService.save(medecin);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/rdv")
    public String addRdv(@RequestParam Long patientId,
                         @RequestParam Long medecinId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRdv,
                         @RequestParam String heureRdv,
                         @RequestParam String symptomes) {

        LocalTime localTime = LocalTime.parse(heureRdv);
        LocalDateTime dateTimeRdv = LocalDateTime.of(dateRdv, localTime);

        Patient patient = patientService.findById(patientId);
        Medecin medecin = medecinService.findById(medecinId);

        if (!rdvService.isCreneauLibre(medecin, dateTimeRdv)) {
            return "redirect:/dashboard?error=heure_occupee";
        }

        RendezVous rdv = new RendezVous();
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        rdv.setDateRdv(dateRdv);
        rdv.setSymptomes(symptomes);

        rdvService.save(rdv);
        return "redirect:/dashboard";
    }
}

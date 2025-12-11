package com.medical.patient.feign;

import com.medical.entities.RendezVous;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rdv-service")  // nom exact enregistré dans Eureka
public interface RendezVousClient {

    @GetMapping("/rdv/patient/{id}")
    List<RendezVous> getRendezVousByPatient(@PathVariable("id") Long patientId);
}


package com.medical.rdv;

import com.medical.patient.PatientServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EntityScan(basePackages = "com.medical.entities")
public class RdvServiceApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(PatientServiceApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE); // ou WebApplicationType.REACTIVE
        app.run(args);
        new CountDownLatch(1).await();

    }
}

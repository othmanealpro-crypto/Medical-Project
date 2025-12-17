package com.medical.patient;
import com.medical.patient.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.medical.patient.feign")
@EntityScan(basePackages = "com.medical.entities")
public class PatientServiceApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(PatientServiceApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE); // ou WebApplicationType.REACTIVE
        app.run(args);
        new CountDownLatch(1).await();


    }
}



package com.medical.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.medical.patient.feign")
@EntityScan(basePackages = "com.medical.entities")
public class PatientServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PatientServiceApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);

        SpringApplication.run(PatientServiceApplication.class, args);
    }
}


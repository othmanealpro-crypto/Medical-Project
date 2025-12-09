package com.medical.rdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.medical.entities")
public class RdvServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RdvServiceApplication.class, args);
    }
}

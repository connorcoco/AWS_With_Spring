package com.example.awswithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AwsWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsWithSpringApplication.class, args);
    }

}

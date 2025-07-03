package com.autonexo;

//http://localhost:8383/swagger-ui/index.html#/cars-controller

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AutonexoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutonexoApplication.class, args);
    }

}

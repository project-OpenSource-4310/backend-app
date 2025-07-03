package com.autonexo;

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

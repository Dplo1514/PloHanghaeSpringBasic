package com.plo.ploboardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PloBoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PloBoardProjectApplication.class, args);
    }

}

package com.esp.banque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties
public class Serveur {

    public static void main(String[] args) {
        SpringApplication.run(Serveur.class, args);
    }

}

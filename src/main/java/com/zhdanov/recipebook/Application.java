package com.zhdanov.recipebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Application {

    @Value("${message}")
    private String message;

    @Value("${myproperty}")
    private String mavenProperty;

    @GetMapping("/")
    String home() {
        return message + mavenProperty;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

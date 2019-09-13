package com.javaedge.guns.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JavaEdge
 */
@SpringBootApplication(scanBasePackages = {"com.javaedge.guns"})
public class GunsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsRestApplication.class, args);
    }
}

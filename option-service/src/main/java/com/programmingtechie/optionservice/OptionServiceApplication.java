package com.programmingtechie.optionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptionServiceApplication.class, args);
    }
}


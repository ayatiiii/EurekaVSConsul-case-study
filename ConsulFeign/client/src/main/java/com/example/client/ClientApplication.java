package com.example.client;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeH2Database(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(Long.parseLong("1"), "Aya IMAGHRI", Float.parseFloat("21")));
            clientRepository.save(new Client(Long.parseLong("2"), "Sarah FAOUZI", Float.parseFloat("27")));
            clientRepository.save(new Client(Long.parseLong("3"), "Mouna FATWI", Float.parseFloat("25")));
        };
    }
}

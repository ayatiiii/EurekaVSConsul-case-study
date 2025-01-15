package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String matricule;

    // Relation ManyToOne pour relier chaque voiture à un client
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // Cette colonne sera utilisée pour lier une voiture à un client
    private Client client;

    public Long getClient_id() {
        return client != null ? client.getId() : null;
    }
}

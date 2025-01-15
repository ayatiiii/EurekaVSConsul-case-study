package com.example.car.models;

import com.example.car.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String matricule;
    private Long id;
    private String brand;
    private String model;
    private Long client_id;

    public CarResponse(Long id, String brand, String model, String matricule, Long client_id) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.matricule = matricule;
        this.client_id = client_id;
    }
}
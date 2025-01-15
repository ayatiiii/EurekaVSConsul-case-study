package com.example.car.controllers;

import com.example.car.entities.Car;
import com.example.car.models.CarResponse;
import com.example.car.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable Long id) throws Exception {
        return carService.findById(id);
    }

    @PostMapping("/add")
    public CarResponse addCar(@RequestBody Car car) {
        // Sauvegarder la voiture en utilisant le service
        Car savedCar = carService.save(car);

        // Construire une réponse en incluant directement le client_id
        return new CarResponse(
                savedCar.getId(),
                savedCar.getBrand(),
                savedCar.getModel(),
                savedCar.getMatricule(),
                savedCar.getClient_id() // Utilisation directe de client_id
        );
    }
}

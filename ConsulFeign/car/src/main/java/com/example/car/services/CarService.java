package com.example.car.services;

import com.example.car.entities.Car;
import com.example.car.entities.Client;
import com.example.car.feign.FeignClient;
import com.example.car.models.CarResponse;
import com.example.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FeignClient clientFeignClient;

    public List<CarResponse> findAll() {
        List<Car> cars = carRepository.findAll();
        Client[] clients = clientFeignClient.getAllClients();
        return cars.stream()
                .map(car -> mapToCarResponse(car, clients))
                .toList();
    }

    private CarResponse mapToCarResponse(Car car, Client[] clients) {
        Client foundClient = Arrays.stream(clients)
                .filter(client -> client.getId().equals(car.getClient_id()))
                .findFirst()
                .orElse(null);

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client_id(car.getClient_id())
                .matricule(car.getMatricule())
                .model(car.getModel())
                .build();
    }

    public CarResponse findById(Long id) throws Exception {
        Car car = carRepository.findById(id).orElseThrow(() -> new Exception("Invalid Car Id"));
        Client client = clientFeignClient.getClientById(car.getClient_id());
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client_id(car.getClient_id())
                .matricule(car.getMatricule())
                .model(car.getModel())
                .build();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
}

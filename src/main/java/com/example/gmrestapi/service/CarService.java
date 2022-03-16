package com.example.gmrestapi.service;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.entity.Car;
import com.example.gmrestapi.repository.AutoshopRepository;
import com.example.gmrestapi.repository.CarRepository;
import com.example.gmrestapi.repository.GMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    final GMRepository gmRepository;
    final CarRepository carRepository;
    final AutoshopRepository autoshopRepository;

    //ctrl +alt + l  cleand code
    public ApiResponse add(Car car) {
//        Car save = new Car();
//        save.setModel(car.getModel());
//        save.setPrice(car.getPrice());
//        save.setYear(car.getYear());
        carRepository.save(car);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(UUID id, Car car) {
//        CarDTO cardto = (CarDTO) dto;

        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Car Not found!", false);

        Car edited = byId.get();
        edited.setModel(car.getModel());
        edited.setPrice(car.getPrice());
        edited.setYear(car.getYear());
        carRepository.save(edited);

        return new ApiResponse("Edited", true);
    }
}

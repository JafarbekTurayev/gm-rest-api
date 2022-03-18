package com.example.gmrestapi.controller;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.entity.Car;
import com.example.gmrestapi.repository.CarRepository;
import com.example.gmrestapi.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    final CarRepository carRepository;
    final CarService carService;

    //getAll
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Car> all = carRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
        //response entity status header body
    }

    //getOne
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        Optional<Car> byId = carRepository.findById(id);

//        return ResponseEntity.status(byId.isEmpty() ? 404 : 200).body(new Car());
        return ResponseEntity.status(byId.isEmpty() ?
                HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new Car()));
    }

    //add
    @PostMapping
    public HttpEntity<?> add(@RequestBody Car car) {
        ApiResponse response = carService.add(car);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody Car car) {
        ApiResponse response = carService.edit(id, car);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        //rostanam o'chirish
//        if (carRepository.existsById(id)) {
//            carRepository.deleteById(id);
//            return ResponseEntity.ok().body("Deleted!");
//        } else {
//            return ResponseEntity.status(404).body("Car NOT found!");
//        }
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()) return ResponseEntity.status(404).body("NOT FOUND");
        Car car = byId.get();
        car.setActive(false);
        carRepository.save(car);
//        return ResponseEntity.status(204).body("DELETED!");
        return ResponseEntity.ok().body("DELETED!");
    }


    //Mashina active  @RequestParam("active") boolean status imkoni bor
    @GetMapping("/change/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id, @RequestParam boolean status) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.get();
        car.setActive(!car.isActive());
        carRepository.save(car);
        return ResponseEntity.ok().body(optionalCar.orElseThrow(RuntimeException::new));
    }

    @GetMapping("/byAutoshop/{id}")
    public HttpEntity<?> getByAutoShop(@PathVariable Integer id) {
        return ResponseEntity.ok().body(carRepository.getAllByAutoshop(id));
    }


}

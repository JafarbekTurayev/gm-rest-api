package com.example.gmrestapi.repository;

import com.example.gmrestapi.entity.Car;
import com.example.gmrestapi.entity.GM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}

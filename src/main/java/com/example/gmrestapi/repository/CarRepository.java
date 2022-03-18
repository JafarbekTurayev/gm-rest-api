package com.example.gmrestapi.repository;

import com.example.gmrestapi.entity.Car;
import com.example.gmrestapi.entity.GM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findAllByActiveTrue();

    @Query(value = "select * from car inner join auto_shop_car_list ascl on car.id = ascl.car_list_id where auto_shop_id=:id", nativeQuery = true)
    List<Car> getAllByAutoshop(Integer id);



}

package com.example.gmrestapi.service;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.dto.AutoShopDTO;
import com.example.gmrestapi.entity.Address;
import com.example.gmrestapi.entity.AutoShop;
import com.example.gmrestapi.entity.Car;
import com.example.gmrestapi.entity.GM;
import com.example.gmrestapi.repository.AddressRepository;
import com.example.gmrestapi.repository.AutoshopRepository;
import com.example.gmrestapi.repository.CarRepository;
import com.example.gmrestapi.repository.GMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutoShopService {

    final AddressRepository addressRepository;
    final AutoshopRepository autoshopRepository;
    final GMRepository gmRepository;
    final CarRepository carRepository;

    //ctrl +alt + l  cleand code
    public ApiResponse add(AutoShopDTO dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);

        AutoShop autoShop = new AutoShop();

        Optional<GM> optionalGM = gmRepository.findById(dto.getGmId());
        if (optionalGM.isEmpty()) return new ApiResponse("Not found!", false);

        autoShop.setGm(optionalGM.get());
        autoShop.setName(dto.getName());

        List<UUID> carIds = dto.getCarIds();
        //1-variant
//        List<Car> carList = new ArrayList<>();
//        for (UUID carId : carIds) {
//
//            Optional<Car> optionalCar = carRepository.findById(carId);
//            if (optionalCar.isEmpty()) return new ApiResponse("NOT FOUND", false);
//            carList.add(optionalCar.get());
//        }

        //2-variant
        List<Car> allById = carRepository.findAllById(dto.getCarIds());

        autoShop.setCarList(allById);
        autoShop.setAddress(save);
        autoshopRepository.save(autoShop);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, AutoShopDTO dto) {

        Optional<AutoShop> byId = autoshopRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("AutoShop Not found!", false);

        AutoShop autoShop = byId.get();

        Address gmAddress = autoShop.getAddress();
        gmAddress.setStreet(dto.getStreet());
        gmAddress.setHome(dto.getHome());
        gmAddress.setCity(dto.getCity());

        autoShop.setAddress(gmAddress);
        autoShop.setName(dto.getName());

        Optional<GM> optionalGM = gmRepository.findById(dto.getGmId());
        autoShop.setGm(optionalGM.get());

        List<Car> allById = carRepository.findAllById(dto.getCarIds());
        autoShop.setCarList(allById);

        autoshopRepository.save(autoShop);
        return new ApiResponse("Edited", true);
    }
}

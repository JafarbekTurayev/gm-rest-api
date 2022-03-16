package com.example.gmrestapi.service;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.dto.AutoShopDTO;
import com.example.gmrestapi.entity.Address;
import com.example.gmrestapi.entity.AutoShop;
import com.example.gmrestapi.repository.AddressRepository;
import com.example.gmrestapi.repository.AutoshopRepository;
import com.example.gmrestapi.repository.GMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutoShopService {

    final AddressRepository addressRepository;
    final AutoshopRepository autoshopRepository;
    final GMRepository gmRepository;

    //ctrl +alt + l  cleand code
    public ApiResponse add(AutoShopDTO dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);

        AutoShop gm = new AutoShop();

        gm.setAddress(save);
        autoshopRepository.save(gm);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, AutoShopDTO dto) {
//        AutoShopDTO gmdto = (AutoShopDTO) dto;

        Optional<AutoShop> byId = autoshopRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("AutoShop Not found!", false);

        AutoShop gm = byId.get();

        Address gmAddress = gm.getAddress();
        gmAddress.setStreet(dto.getStreet());
        gmAddress.setHome(dto.getHome());
        gmAddress.setCity(dto.getCity());

        gm.setAddress(gmAddress);
        autoshopRepository.save(gm);

        return new ApiResponse("Edited", true);
    }
}

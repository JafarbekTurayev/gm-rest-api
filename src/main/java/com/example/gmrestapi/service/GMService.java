package com.example.gmrestapi.service;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.dto.GMDTO;
import com.example.gmrestapi.entity.Address;
import com.example.gmrestapi.entity.GM;
import com.example.gmrestapi.repository.AddressRepository;
import com.example.gmrestapi.repository.GMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GMService {

    final GMRepository gmRepository;
    final AddressRepository addressRepository;

    //ctrl +alt + l  cleand code
    public ApiResponse add(GMDTO dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);

        GM gm = new GM();
        gm.setDirector(dto.getDirector());
        gm.setCorpName(dto.getCorpName());
        gm.setAddress(save);
        gmRepository.save(gm);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(UUID id, GMDTO dto) {
//        GMDTO gmdto = (GMDTO) dto;

        Optional<GM> byId = gmRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("GM Not found!", false);

        GM gm = byId.get();
        gm.setDirector(dto.getDirector());
        gm.setCorpName(dto.getCorpName());

        Address gmAddress = gm.getAddress();
        gmAddress.setStreet(dto.getStreet());
        gmAddress.setHome(dto.getHome());
        gmAddress.setCity(dto.getCity());

        gm.setAddress(gmAddress);
        gmRepository.save(gm);

        return new ApiResponse("Edited", true);
    }
}

package com.example.gmrestapi.controller;

import com.example.gmrestapi.dto.ApiResponse;
import com.example.gmrestapi.dto.GMDTO;
import com.example.gmrestapi.entity.GM;
import com.example.gmrestapi.repository.CarRepository;
import com.example.gmrestapi.repository.GMRepository;
import com.example.gmrestapi.service.GMService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/gm")
@RequiredArgsConstructor
public class GMController {

    final GMRepository gmRepository;
    final CarRepository carRepository;
    final GMService gmService;

    //getAll
    @GetMapping
    public HttpEntity<?> getAll() {
        List<GM> all = gmRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
        //response entity status header body
    }

    //getOne
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        Optional<GM> byId = gmRepository.findById(id);

//        return ResponseEntity.status(byId.isEmpty() ? 404 : 200).body(new GM());
        return ResponseEntity.status(byId.isEmpty() ?
                HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new GM()));
    }

    //add
    @PostMapping
    public HttpEntity<?> add(@RequestBody GMDTO dto) {
        ApiResponse response = gmService.add(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody GMDTO gmdto) {
        ApiResponse response = gmService.edit(id, gmdto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        //rostanam o'chirish
//        if (gmRepository.existsById(id)) {
//            gmRepository.deleteById(id);
//            return ResponseEntity.ok().body("Deleted!");
//        } else {
//            return ResponseEntity.status(404).body("GM NOT found!");
//        }
        Optional<GM> byId = gmRepository.findById(id);
        if (byId.isEmpty()) return ResponseEntity.status(404).body("NOT FOUND");
        GM gm = byId.get();
        gm.setActive(false);
        gmRepository.save(gm);
//        return ResponseEntity.status(204).body("DELETED!");
        return ResponseEntity.ok().body("DELETED!");
    }

}

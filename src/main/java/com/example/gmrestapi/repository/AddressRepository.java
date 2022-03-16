package com.example.gmrestapi.repository;

import com.example.gmrestapi.entity.Address;
import com.example.gmrestapi.entity.GM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

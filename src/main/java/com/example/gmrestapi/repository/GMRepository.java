package com.example.gmrestapi.repository;

import com.example.gmrestapi.entity.GM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GMRepository extends JpaRepository<GM, UUID> {
    List<GM> findAllByActiveTrue();
}

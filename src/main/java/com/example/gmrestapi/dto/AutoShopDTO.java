package com.example.gmrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoShopDTO {
    private String name;
    private UUID gmId;
    private List<UUID> carIds;
    private String home;
    private String street;
    private String city;

}

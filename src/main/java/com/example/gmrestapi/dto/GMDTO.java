package com.example.gmrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GMDTO {

    private String corpName;
    private String director;
    private String home;
    private String street;
    private String city;

}

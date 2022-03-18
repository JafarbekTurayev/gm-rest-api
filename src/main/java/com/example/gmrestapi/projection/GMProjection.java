package com.example.gmrestapi.projection;


import com.example.gmrestapi.entity.Address;
import org.springframework.beans.factory.annotation.Value;

public interface GMProjection {

    String getCorpName();

    String getDirector();

//    Address getAddress();


    @Value("#{target.address.city + ' '  + target.address.street + ' ' + target.address.home}")
    String getAddressName();


}

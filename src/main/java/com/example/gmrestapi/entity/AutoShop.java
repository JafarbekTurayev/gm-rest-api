package com.example.gmrestapi.entity;

import com.example.gmrestapi.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoShop extends AbsNameEntity {

    @ManyToOne
    private GM gm;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Car> carList;


}

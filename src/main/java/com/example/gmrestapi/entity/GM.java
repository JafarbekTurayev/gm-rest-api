package com.example.gmrestapi.entity;

import com.example.gmrestapi.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class GM extends AbsEntity {
    //50 ta 10ta
    private String corpName;

    private String director;

    @OneToOne
    private Address address;

}

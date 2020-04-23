package com.tfg.server.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Producte")
@Data
public class Producte {
    public Producte(){
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @NotBlank
    @Length(max=64)
    String name;

    @Length(max=512)
    String description;

    @NotNull
    Boolean active = true;


}

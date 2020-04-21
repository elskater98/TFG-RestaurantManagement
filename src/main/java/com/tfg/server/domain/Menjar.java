package com.tfg.server.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.util.*;

@Entity
@Table(name = "Menjar")
@Data
public class Menjar {

    public Menjar(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;


    @NotBlank
    @Length(max = 64)
    private String name;

    @NotNull
    Integer quantity;

    @OneToMany
    private List<Producte> ingredients;

}

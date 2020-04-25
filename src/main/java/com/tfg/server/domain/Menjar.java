package com.tfg.server.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Producte> ingredients;

}

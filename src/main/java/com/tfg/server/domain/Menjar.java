package com.tfg.server.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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

    @NotBlank
    private String type = "Unknown";

    @Length(max = 512)
    private String description;

    @NotNull
    private Boolean enable=true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Producte> ingredients;

}

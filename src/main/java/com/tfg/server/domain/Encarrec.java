package com.tfg.server.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Encarrec")
@Data
public class Encarrec {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    List<Menjar> menjar;

    @NotNull
    Integer quantity;

}

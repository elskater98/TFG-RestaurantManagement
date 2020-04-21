package com.tfg.server.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Encarrec")
@Data

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Encarrec {

    @Id
    private UUID id= UUID.randomUUID();

    @NotBlank
    @Length(max = 64)
    String client;

    @OneToMany
    List<Menjar> menjar;



}

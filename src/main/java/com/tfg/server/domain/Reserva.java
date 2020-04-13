package com.tfg.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Reserva")
@Data

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Reserva {

    public Reserva(){

    }

    @Id
    private UUID id= UUID.randomUUID();

    @NotBlank
    @Length(min = 1, max = 64, message = "Client length is outnumberd 64 characters.")
    private String client;

    @Range(min=1,max = 32)
    @NotNull(message = "The number of people may not be empty.")
    private Integer people;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

    @NotNull
    private boolean inside=true;

    @Length(max = 32)
    String mobile;

    @Email
    String email;

    @Length(max = 512)
    String observations;
    
}

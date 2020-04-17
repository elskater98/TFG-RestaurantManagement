package com.tfg.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.cfg.Environment;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Configuration;

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

    @Range(min=1,max = 64)
    @NotNull(message = "The number of people may not be empty.")
    private Integer people;

    @NotNull(message = "The number of people may not be empty.")
    private String subId;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    private String dateString;

    @NotNull
    @Length(min=5,max = 5)
    private String hour;

    @NotNull
    private boolean inside=true;

    public boolean getInside(){
        return this.inside;
    }

    @Length(max = 32)
    String mobile;

    @Email
    String email;

    @Length(max = 512)
    String observations;

    public UUID getId() {
        return this.id;
    }

}

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

    @NotNull(message = "The number of people may not be empty.")
    private String subId;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;

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

    public String generateSubId(Reserva reserva){
        String date = reserva.getDate().toInstant().toString();
        String [] parts = date.split("T");
        int hour = Integer.parseInt(parts[1].substring(0,2));

        if(hour >=12 && hour<=16){
            return parts[0]+"Lunch";
        }else if(hour>=19 && hour<=23){
            return parts[0]+"Diner";
        }
        return "";
    }

    public UUID getId() {
        return this.id;
    }

}

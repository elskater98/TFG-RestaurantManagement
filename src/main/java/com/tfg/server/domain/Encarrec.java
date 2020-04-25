package com.tfg.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "Encarrec")
@Data
public class Encarrec {

    @Id
    private UUID id= UUID.randomUUID();

    @NotBlank
    @Length(max = 64)
    String client;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    private String dateString;

    @NotNull
    @Length(min=5,max = 5)
    private String hour;

    @NotNull
    private Boolean delivery=true;

    @Length(max = 32)
    String mobile;

    @Email
    String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull
    List<Menjar> menjars;

    // Format: Quantitymenjar1;Quantitymenjar2;
    @NotNull
    String quantity;

    public boolean getDelivery(){
        return this.delivery;
    }

    public UUID getId() {
        return this.id;
    }

    @Transactional
    public String generateSubDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Length(max = 512)
    String observations;

}

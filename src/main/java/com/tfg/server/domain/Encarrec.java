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

    // TO DO: @NotNull Possiblement no fagi falta
    // private String clientUUID;

    @NotBlank
    @Length(max = 64)
    private String client;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    private String dateString;

    @NotNull
    @Length(min=5,max = 5)
    private String hour;

    @NotNull
    private Boolean takeaway;

    @Length(max = 32)
    private String mobile;

    @Email
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Menjar> menjars;

    @NotNull
    Boolean status=false;

    // Format: Quantitymenjar1;Quantitymenjar2;
    @NotNull
    private String quantity;


    @NotNull
    private String employee;

    @Length(max = 512)
    private String observations;

    public boolean getTakeway(){
        return this.takeaway;
    }

    public UUID getId() {
        return this.id;
    }

    @Transactional
    public String generateSubDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}

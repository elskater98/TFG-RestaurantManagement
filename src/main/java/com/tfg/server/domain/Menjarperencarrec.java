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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Menjarperencarrec")
@Data

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Menjarperencarrec {

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

    @OneToMany(cascade = CascadeType.PERSIST)
    @NotNull
    List<Encarrec> encarrecs;

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

}

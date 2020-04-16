package com.tfg.server.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BadRequestException;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.ReservaRepository;
import com.tfg.server.services.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@RepositoryEventHandler
public class ReservaEventHandler {

    @Value("${aforament_inside}")
    int inside;

    @Value("${aforament_outside}")
    int outsite;

    int count;

    HashMap<String, ArrayList<Reserva>> totalAforament = new HashMap<String, ArrayList<Reserva>>();

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    ReservaService reservaService;

    final Logger logger = LoggerFactory.getLogger(Reserva.class);

    @HandleBeforeCreate
    public void handleReservaPreCreate(Reserva reserva) throws BasicException {
        String subId = reservaService.generateSubId(reserva.getDate());

        if(!subId.contains("Diner") && !subId.contains("Lunch")){
            throw new BasicException("The date is not allowed.");
        }

        count= 0;
        for (Reserva res:reservaRepository.findBySubIdAndInside(subId,reserva.getInside())) {
            count+=res.getPeople();
        }

        if(reserva.getInside() && count+reserva.getPeople()>inside){
                throw new BadRequestException();
        }else if(!reserva.getInside() && count+reserva.getPeople()>outsite)  {
                throw new BadRequestException();
        }
        reserva.setSubDate(this.reservaService.generateSubDate(reserva.getDate())[0]);
        reserva.setSubId(subId);


        logger.info("Before creating: {}", reserva.toString());
    }

    @HandleBeforeSave
    public void handleReservaPreSave( Reserva reserva) throws BasicException {
        String subId = reservaService.generateSubId(reserva.getDate());

        if(!subId.contains("Diner") && !subId.contains("Lunch")){
            throw new BasicException("The date is not allowed.");
        }

        count=0;
        for (Reserva res : reservaRepository.findBySubIdAndInside(subId,reserva.getInside())){
            if(res.getId().equals(reserva.getId())){
                count-=res.getPeople();
            }
            count+=res.getPeople();
        }

        if(reserva.getInside() && count + reserva.getPeople()>inside){
            throw new BadRequestException();
        }else if(!reserva.getInside() && count + reserva.getPeople()>outsite)  {
            throw new BadRequestException();
        }
        reserva.setSubDate(this.reservaService.generateSubDate(reserva.getDate())[0]);
        reserva.setSubId(subId);

        logger.info("Before saving: {}",reserva.toString());
    }
}

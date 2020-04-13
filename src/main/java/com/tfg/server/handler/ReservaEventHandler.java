package com.tfg.server.handler;

import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BadRequestException;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
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

    HashMap<String, Integer> totalInsite = new HashMap<String, Integer>();
    HashMap<String, Integer> totalOutsite = new HashMap<String, Integer>();

    @Autowired
    ReservaRepository reservaRepository;

    final Logger logger = LoggerFactory.getLogger(Reserva.class);

    @HandleBeforeCreate
    public void handleReservaPreCreate(Reserva reserva) throws BasicException {

        totalInsite.putIfAbsent(getKeyByReserva(reserva), 0);
        totalOutsite.putIfAbsent(getKeyByReserva(reserva), 0);
        if(reserva.getInside()){
            if(reserva.getPeople()+totalInsite.get(getKeyByReserva(reserva)) > inside){
                throw new BasicException("Sold out - insite");
            }
        }else {
            if(reserva.getPeople()+totalOutsite.get(getKeyByReserva(reserva)) > outsite){
                throw new BasicException("Sold out - outsite");
            }
        }

        logger.info("Before creating: {}",reserva.toString());
    }

    @HandleAfterCreate
    public void handleReservaPostCreate(Reserva reserva) {
        int aux;

        if(reserva.getInside()){
            aux = totalInsite.get(getKeyByReserva(reserva));
            totalInsite.put(getKeyByReserva(reserva),reserva.getPeople()+aux);
        }else {
            aux = totalOutsite.get(getKeyByReserva(reserva));
            totalOutsite.put(getKeyByReserva(reserva),reserva.getPeople()+aux);
        }

        logger.info("After creating: {}", reserva.toString());
    }

    public String getKeyByReserva(Reserva reserva){
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
}

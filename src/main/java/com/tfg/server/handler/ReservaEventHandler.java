package com.tfg.server.handler;

import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BadRequestException;
import com.tfg.server.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryEventHandler
public class ReservaEventHandler {

    @Value("${aforament_inside}")
    int inside;

    @Value("${aforament_outside}")
    int outsite;

    int currentAforament = 0;

    @Autowired
    ReservaRepository reservaRepository;

    final Logger logger = LoggerFactory.getLogger(Reserva.class);

    @HandleBeforeCreate
    public void handleReservaPreCreate(Reserva reserva) {

        if(currentAforament + reserva.getPeople() > (outsite+inside)){
            throw new BadRequestException();
        }

        logger.info("Before creating: {}", reserva.toString());
    }

    @HandleAfterCreate
    public void handleReservaPostCreate(Reserva reserva){
        //Comprovar el dia
        //Si laforament es interior o exterior
        currentAforament+=reserva.getPeople();
        logger.info("After creating: {}", reserva.toString()+"Aforament:"+currentAforament+"/"+(outsite+inside));
    }
}

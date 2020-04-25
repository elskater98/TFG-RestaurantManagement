package com.tfg.server.handler;

import com.tfg.server.domain.Encarrec;
import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.EncarrecRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class EncarrecEventHandler {
    @Autowired
    EncarrecRepository encarrecRepository;

    final Logger logger = LoggerFactory.getLogger(Encarrec.class);


    @HandleBeforeCreate
    public void handleEncarrecPreCreate(Encarrec encarrec){

        encarrec.setDateString(encarrec.generateSubDate(encarrec.getDate()));

        logger.info("Before creating: {}", encarrec.toString());
    }

    @HandleBeforeSave
    public void handleEncarrecPreSave(Encarrec encarrec){

        encarrec.setDateString(encarrec.generateSubDate(encarrec.getDate()));

        logger.info("Before creating: {}", encarrec.toString());
    }
}

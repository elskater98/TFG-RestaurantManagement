package com.tfg.server.handler;

import com.tfg.server.domain.Menjarperencarrec;
import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.MenjarPerEncarrecRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class MenjarperencarrecEventHandler {
    @Autowired
    MenjarPerEncarrecRepository menjarPerEncarrecRepository;

    final Logger logger = LoggerFactory.getLogger(Menjarperencarrec.class);

    @HandleBeforeCreate
    public void handleMenjarperencarrecPreCreate(Menjarperencarrec menjarperencarrec) throws BasicException {

        menjarperencarrec.setDateString(menjarperencarrec.generateSubDate(menjarperencarrec.getDate()));

        logger.info("Before creating: {}", menjarperencarrec.toString());
    }

    @HandleBeforeSave
    public void handleMenjarperencarrecPreSave(Menjarperencarrec menjarperencarrec) throws BasicException {

        menjarperencarrec.setDateString(menjarperencarrec.generateSubDate(menjarperencarrec.getDate()));

        logger.info("Before save: {}", menjarperencarrec.toString());
    }
}

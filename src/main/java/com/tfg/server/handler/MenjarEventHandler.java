package com.tfg.server.handler;

import com.tfg.server.domain.Menjar;
import com.tfg.server.domain.Producte;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.MenjarRepository;
import com.tfg.server.repository.ProducteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class MenjarEventHandler {
    @Autowired
    MenjarRepository menjarRepository;

    final Logger logger = LoggerFactory.getLogger(Menjar.class);

    @HandleBeforeCreate
    public void handleProductePreCreate(Menjar menjar) throws BasicException {
        if(menjarRepository.findByName(menjar.getName())!=null) {
            throw new BasicException("Menjar " + menjar.getName() + " already exists.");
        }


        logger.info("Before creating: {}", menjar.toString());
    }

}

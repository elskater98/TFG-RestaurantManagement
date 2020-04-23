package com.tfg.server.handler;

import com.tfg.server.domain.Menjarperencarrec;
import com.tfg.server.domain.Producte;
import com.tfg.server.domain.Reserva;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.MenjarPerEncarrecRepository;
import com.tfg.server.repository.ProducteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ProducteEventHandler {

    @Autowired
    ProducteRepository producteRepository;

    final Logger logger = LoggerFactory.getLogger(Producte.class);

    @HandleBeforeCreate
    public void handleProductePreCreate(Producte producte) throws BasicException {
        if(producteRepository.findByName(producte.getName())!=null) {
            throw new BasicException("Product " + producte.getName() + " already exists.");
        }


        logger.info("Before creating: {}", producte.toString());
    }

    @HandleBeforeSave
    public void handleProductePreSave(Producte producte) throws BasicException {
        if(producteRepository.findByName(producte.getName())!=null) {
            throw new BasicException("Product " + producte.getName() + " already exists.");
        }


        logger.info("Before save: {}", producte.toString());
    }
}

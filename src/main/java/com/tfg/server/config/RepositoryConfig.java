package com.tfg.server.config;

import com.tfg.server.domain.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Reserva.class);
        config.exposeIdsFor(Menjar.class);
        config.exposeIdsFor(Encarrec.class);
        config.exposeIdsFor(Producte.class);
    }
}

package com.tfg.server.repository;

import com.tfg.server.domain.Producte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;
@RepositoryRestResource(excerptProjection = Producte.class)
public interface ProducteRepository extends CrudRepository<Producte, Integer> {

    Producte findByName(String name);

}

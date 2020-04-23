package com.tfg.server.repository;

import com.tfg.server.domain.Menjar;
import com.tfg.server.domain.Producte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = Menjar.class)
public interface MenjarRepository extends CrudRepository<Menjar, Integer> {
    Menjar findByName(String str);
}

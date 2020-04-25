package com.tfg.server.repository;

import com.tfg.server.domain.Encarrec;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;


@RepositoryRestResource(excerptProjection = Encarrec.class)
public interface EncarrecRepository extends CrudRepository<Encarrec, UUID> {
}

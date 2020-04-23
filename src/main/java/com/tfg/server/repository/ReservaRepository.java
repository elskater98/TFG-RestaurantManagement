package com.tfg.server.repository;

import com.tfg.server.domain.Encarrec;
import com.tfg.server.domain.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.*;
@RepositoryRestResource(excerptProjection = Reserva.class)
public interface ReservaRepository extends CrudRepository<Reserva, UUID> {

    List<Reserva> findByDateString(String date);
    List<Reserva> findByDateStringAndInside(String date,Boolean bool);

    List<Reserva> findBySubIdAndInside(String subId, Boolean bool);

    Optional<Reserva> findById(UUID id);

}

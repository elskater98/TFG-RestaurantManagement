package com.tfg.server.repository;

import com.tfg.server.domain.Reserva;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.*;

public interface ReservaRepository extends PagingAndSortingRepository<Reserva, UUID> {

    List<Reserva> findByDateString(String date);
    List<Reserva> findByDateStringAndInside(String date,Boolean bool);

    List<Reserva> findBySubIdAndInside(String subId, Boolean bool);

    Optional<Reserva> findById(UUID id);

}

package com.tfg.server.repository;

import com.tfg.server.domain.Reserva;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.*;

public interface ReservaRepository extends PagingAndSortingRepository<Reserva, UUID> {
    List<Reserva> findByClient(String string);
    List<Reserva> findByDateAndClient(Date date,String string);
    List<Reserva> findByDate(Date date);
    List<Reserva> findByDateAndInside(Date date,Boolean bool);
    ArrayList<Reserva> findBySubIdAndInside(String str, Boolean bool);
    Optional<Reserva> findById(UUID id);

}

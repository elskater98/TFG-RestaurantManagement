package com.tfg.server.repository;

import com.tfg.server.domain.Reserva;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.*;

public interface ReservaRepository extends PagingAndSortingRepository<Reserva, UUID> {
    Reserva findByClientAndAndDate(String client,Date date);
    List<Reserva> findByClient(String string);
    List<Reserva> findByDate(Date date);
    ArrayList<Reserva> findBySubIdAndInside(String str, Boolean bool);

}

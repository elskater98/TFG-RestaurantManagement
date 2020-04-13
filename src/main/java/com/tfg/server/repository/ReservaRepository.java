package com.tfg.server.repository;

import com.tfg.server.domain.Reserva;
import com.tfg.server.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends PagingAndSortingRepository<Reserva, UUID> {

    List<Reserva> findByClient(String string);
    List<Reserva> findByDate(Date date);
    List<Reserva> findByInside(Boolean bool);

}

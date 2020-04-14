package com.tfg.server.services;

import com.tfg.server.domain.Reserva;
import com.tfg.server.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Transactional
    public List<Reserva> findByDate(Date date){
        return reservaRepository.findByDate(date);
    }

    @Transactional
    public List<Reserva> findByDateAndInside(Date date,Boolean bool){
        return reservaRepository.findByDateAndInside(date,bool);
    }

    @Transactional
    public List<Reserva> findByDateAndClient(Date date, String client){
        return reservaRepository.findByDateAndClient(date,client);
    }

    @Transactional
    public Optional<Reserva> findById(UUID uuid){
        return reservaRepository.findById(uuid);
    }
}

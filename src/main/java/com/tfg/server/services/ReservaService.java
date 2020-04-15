package com.tfg.server.services;

import com.tfg.server.domain.Reserva;
import com.tfg.server.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Component
public class ReservaService {
    @Value("${start_diner}")
    int start_diner;
    @Value("${end_diner}")
    int end_diner;
    @Value("${start_lunch}")
    int start_lunch;
    @Value("${end_lunch}")
    int end_lunch;


    @Autowired
    ReservaRepository reservaRepository;

    @Transactional
    public List<Reserva> findByDate(String date){
        return reservaRepository.findBySubDate(date);
    }

    @Transactional
    public List<Reserva> findByDateAndInside(String date,Boolean bool){
        return reservaRepository.findBySubDateAndInside(date,bool);
    }

    @Transactional
    public String generateSubId(Date date_r){
        String date = date_r.toInstant().toString();
        String [] parts = date.split("T");
        int hour = Integer.parseInt(parts[1].substring(0,2));

        if(hour >=start_lunch && hour<=end_lunch){
            return parts[0]+" Lunch";
        }else if(hour>=start_diner && hour<=end_diner){
            return parts[0]+" Diner";
        }
        return "";
    }
}

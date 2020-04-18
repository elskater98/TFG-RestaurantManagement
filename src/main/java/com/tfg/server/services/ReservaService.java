package com.tfg.server.services;

import com.tfg.server.domain.Reserva;
import com.tfg.server.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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
        return reservaRepository.findByDateString(date);
    }

    @Transactional
    public List<Reserva> findByDateAndInside(String date,Boolean bool){
        return reservaRepository.findByDateStringAndInside(date,bool);
    }

    @Transactional
    public List<Reserva> findBySubIdAndInside(String subId, Boolean bool){
        return reservaRepository.findBySubIdAndInside(subId,bool);
    }

    @Transactional
    public String generateSubId(Date date,String hour){
        String parts_date = generateSubDate(date);
        String [] parts_hour = hour.split(":");

        int subHour = Integer.parseInt(parts_hour[0]);

        if(subHour >=start_lunch && subHour<=end_lunch){
            return parts_date+" Lunch";
        }else if(subHour>=start_diner && subHour<=end_diner){
            return parts_date+" Diner";
        }
        return "";
    }

    @Transactional
    public String generateSubDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Transactional
    public Integer countReservasInsite(String subId,Boolean bool){
        return reservaRepository.countBySubIdAndInside(subId,bool);
    }
}

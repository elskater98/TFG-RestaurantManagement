package com.tfg.server.repository;

import com.tfg.server.domain.Producte;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProducteRepository extends PagingAndSortingRepository<Producte, Integer> {

    Producte findByName(String name);

}

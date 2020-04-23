package com.tfg.server.repository;

import com.tfg.server.domain.Menjar;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface MenjarRepository extends PagingAndSortingRepository<Menjar, Integer> {
    Menjar findByName(String str);
}

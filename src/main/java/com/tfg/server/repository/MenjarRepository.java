package com.tfg.server.repository;

import com.tfg.server.domain.Menjar;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MenjarRepository extends PagingAndSortingRepository<Menjar, Integer> {
}

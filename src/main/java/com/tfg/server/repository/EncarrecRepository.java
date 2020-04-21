package com.tfg.server.repository;

import com.tfg.server.domain.Encarrec;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface EncarrecRepository extends PagingAndSortingRepository<Encarrec, UUID> {
}

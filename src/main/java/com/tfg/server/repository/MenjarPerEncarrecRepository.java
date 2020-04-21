package com.tfg.server.repository;

import com.tfg.server.domain.MenjarPerEncarrec;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MenjarPerEncarrecRepository extends PagingAndSortingRepository<MenjarPerEncarrec, UUID> {
}

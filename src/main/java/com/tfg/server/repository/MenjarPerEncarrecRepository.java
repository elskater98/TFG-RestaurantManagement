package com.tfg.server.repository;

import com.tfg.server.domain.Menjarperencarrec;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MenjarPerEncarrecRepository extends PagingAndSortingRepository<Menjarperencarrec, UUID> {
}

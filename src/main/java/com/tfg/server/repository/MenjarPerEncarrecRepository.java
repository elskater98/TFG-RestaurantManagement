package com.tfg.server.repository;

import com.tfg.server.domain.Menjarperencarrec;
import com.tfg.server.domain.Producte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;
@RepositoryRestResource(excerptProjection = Menjarperencarrec.class)
public interface MenjarPerEncarrecRepository extends CrudRepository<Menjarperencarrec, UUID> {
}

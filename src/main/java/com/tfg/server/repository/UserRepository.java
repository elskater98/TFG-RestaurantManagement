package com.tfg.server.repository;

import com.tfg.server.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, String> {

  List<User> findByUsernameContaining(@Param("text") String text);
  User findByUsername(String username);
  User findByEmail(String email);
  List<User> findUserByRole(String role);
}

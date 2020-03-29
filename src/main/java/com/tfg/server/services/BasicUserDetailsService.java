package com.tfg.server.services;

import com.tfg.server.domain.User;
import com.tfg.server.exception.BadRequestException;
import com.tfg.server.exception.BasicException;
import com.tfg.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class BasicUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }

  @Transactional
  public  User registerUser(User user) throws BasicException{
    if(userRepository.findByUsername(user.getUsername())!=null){
      throw new BasicException("Username "+user.getUsername()+" already exists.");
    }else if(userRepository.findByEmail(user.getEmail())!=null){
      throw new BasicException("Email"+user.getEmail()+"already exists.");
    }else if(user.getRole() == null){
      throw new BasicException("Rol mustn't null");
    }else if( !user.checkRole(user.getRole())){
      throw new BasicException(user.getRole()+"is not available role.");
    }

    user.encodePassword();
    return userRepository.save(user);
  }

  @Transactional
  public List<User> getUsersByRole(String role) throws BasicException {
    switch (role){
      case "Admin":
      case "Propietari":
      case "Cuiner":
      case "Cambrer":
      case "Bartender":
        return userRepository.findByRole(role);
    }
    throw new BasicException(role +"is not available role.");
  }
}

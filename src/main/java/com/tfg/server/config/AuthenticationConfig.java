package com.tfg.server.config;


import com.tfg.server.domain.Menjar;
import com.tfg.server.domain.Producte;
import com.tfg.server.domain.User;
import com.tfg.server.repository.*;
import com.tfg.server.services.BasicUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

  @Value("${default-password}")
  String defaultPassword;

  @Autowired
  BasicUserDetailsService basicUserDetailsService;
  @Autowired UserRepository userRepository;
  @Autowired ProducteRepository producteRepository;
  @Autowired MenjarRepository menjarRepository;


  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(basicUserDetailsService)
        .passwordEncoder(User.passwordEncoder);

    //Admin User
    if (!userRepository.existsById("admin")) {
      User user = new User();
      user.setEmail("admin@admin.com");
      user.setUsername("admin");
      user.setPassword(defaultPassword);
      user.encodePassword();
      user.setRole("Admin");
      user.setName("Admin");
      user.setSurname("Admin");
      userRepository.save(user);
    }

    Menjar menjar = new Menjar();
    menjar.setName("Macarrons amb formatge");
    ArrayList<Producte> productes = new ArrayList<>();

    Producte formatge= new Producte();
    formatge.setName("Formatge");

    Producte macarrons= new Producte();
    macarrons.setName("Macarrons");

    productes.add(formatge);
    productes.add(macarrons);

    menjar.setIngredients(productes);
    menjarRepository.save(menjar);


  }
}

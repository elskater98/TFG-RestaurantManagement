package com.tfg.server.config;


import com.tfg.server.domain.User;
import com.tfg.server.repository.UserRepository;
import com.tfg.server.services.BasicUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

  @Value("${default-password}")
  String defaultPassword;

  @Autowired
  BasicUserDetailsService basicUserDetailsService;
  @Autowired UserRepository userRepository;

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
      userRepository.save(user);
    }
  }
}

package com.tfg.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${allowed-origins}")
    String[] allowedOrigins;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/*").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/*").hasAnyRole("ADMIN","PROPIETARI")
                .antMatchers(HttpMethod.DELETE, "/users/*").hasAnyRole("ADMIN","PROPIETARI")

                .antMatchers(HttpMethod.GET,"/reservas").permitAll()
                .antMatchers(HttpMethod.GET,"/reservas/*").permitAll()
                .antMatchers(HttpMethod.POST,"/reservas/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")
                .antMatchers(HttpMethod.PATCH,"/reservas/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")
                .antMatchers(HttpMethod.DELETE,"/reservas/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")

                .antMatchers(HttpMethod.GET,"/productes").permitAll()
                .antMatchers(HttpMethod.GET,"/getProducts").permitAll()
                .antMatchers(HttpMethod.GET,"/getCart").permitAll()
                .antMatchers(HttpMethod.GET,"/getBlackList").permitAll()
                .antMatchers(HttpMethod.POST,"/getProductByName").permitAll()
                .antMatchers(HttpMethod.GET,"/productes/*").permitAll()
                .antMatchers(HttpMethod.POST,"/productes/*").hasAnyRole("ADMIN","PROPIETARI")
                .antMatchers(HttpMethod.PATCH,"/productes/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER","CUINER")
                .antMatchers(HttpMethod.DELETE,"/productes/*").hasAnyRole("ADMIN","PROPIETARI")

                .antMatchers(HttpMethod.GET,"/menjars").permitAll()
                .antMatchers(HttpMethod.GET,"/getMenjars").permitAll()
                .antMatchers(HttpMethod.GET,"/menjars/*").permitAll()
                .antMatchers(HttpMethod.POST,"/menjars/*").hasAnyRole("ADMIN","PROPIETARI","CUINER")
                .antMatchers(HttpMethod.PATCH,"/menjars/*").hasAnyRole("ADMIN","PROPIETARI","CUINER")
                .antMatchers(HttpMethod.DELETE,"/menjars/*").hasAnyRole("ADMIN","PROPIETARI")

                .antMatchers(HttpMethod.GET,"/encarrecs").permitAll()
                .antMatchers(HttpMethod.GET,"/encarrecs/*").permitAll()
                .antMatchers(HttpMethod.POST,"/encarrecs/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")
                .antMatchers(HttpMethod.PATCH,"/encarrecs/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")
                .antMatchers(HttpMethod.DELETE,"/encarrecs/*").hasAnyRole("ADMIN","PROPIETARI","CAMBRER","BARTENDER")
                
                .antMatchers(HttpMethod.GET, "/identity").authenticated()
                .antMatchers(HttpMethod.POST, "/register").hasAnyRole("ADMIN","PROPIETARI")

                .antMatchers(HttpMethod.GET, "/getUsersByRole").permitAll()
                .antMatchers(HttpMethod.GET, "/getAllRoles").permitAll()

                .antMatchers(HttpMethod.POST, "/getByDate").permitAll()
                .antMatchers(HttpMethod.POST, "/getReservaInseteByDate").permitAll()
                .antMatchers(HttpMethod.POST, "/getReservaOutsiteByDate").permitAll()
                .antMatchers(HttpMethod.POST, "/findBySubIdAndInside").permitAll()
                .antMatchers(HttpMethod.POST, "/findBySubIdAndOutside").permitAll()

                .anyRequest().permitAll()
                .and()
                .httpBasic().realmName("Server")
                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}

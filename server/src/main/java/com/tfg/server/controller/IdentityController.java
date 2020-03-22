package com.tfg.server.controller;

import com.tfg.server.domain.User;
import com.tfg.server.exception.BasicException;
import com.tfg.server.services.BasicUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

@BasePathAwareController
public class IdentityController {

  @Autowired
  BasicUserDetailsService userService;

  @RequestMapping("/identity")
  public @ResponseBody
  PersistentEntityResource getAuthenticatedUserIdentity(
      PersistentEntityResourceAssembler resourceAssembler) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return resourceAssembler.toResource(user);
  }

  @RequestMapping(value="/register",method = POST)
  @ResponseBody
  public String registerUser(@RequestBody User user) throws BasicException{
    User registeredUser = userService.registerUser(user);
    return registeredUser.getUsername()+"has been created succesfully";
  }
}

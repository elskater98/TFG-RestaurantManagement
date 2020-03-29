package com.tfg.server.controller;

import com.tfg.server.domain.User;
import com.tfg.server.exception.BasicException;
import com.tfg.server.services.BasicUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@BasePathAwareController
public class UserController {

    @Autowired
    BasicUserDetailsService userService;


    @RequestMapping(value = "/getAllRoles", method = GET)
    @ResponseBody
    public List<String> getAllRoles(){
        ArrayList<String> roles = new ArrayList<>(Arrays.asList("Admin","Propietari","Cuiner",
                "Cambrer","Bartender"));
        Collections.sort(roles);
        return roles;
    }

    @RequestMapping(value = "/getUsersByRole", method = GET)
    @ResponseBody
    public List<User> getUsersByRole(@RequestBody String role) throws BasicException {
        return userService.getUsersByRole(role);
    }

}

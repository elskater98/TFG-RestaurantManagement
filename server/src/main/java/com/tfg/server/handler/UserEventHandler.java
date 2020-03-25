package com.tfg.server.handler;

import com.tfg.server.domain.User;
import com.tfg.server.exception.BadRequestException;
import com.tfg.server.exception.ForbiddenException;
import com.tfg.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    final Logger logger = LoggerFactory.getLogger(User.class);

    @Autowired
    UserRepository userRepository;

    @HandleBeforeCreate
    public void handleUserPreCreate(User user) {
        logger.info("Before creating: {}", user.toString());
    }

    @HandleBeforeSave
    public void handleUserPreSave(User user){
        logger.info("Before updating: {}", user.toString());

            if(!user.checkRole(user.getRole())){
                throw new BadRequestException();
            }
    }

    @HandleBeforeDelete
    public void handleUserPreDelete(User user) {
        logger.info("Before deleting: {}", user.toString());
    }

    @HandleBeforeLinkSave
    public void handleUserPreLinkSave(User user, Object o) {
        logger.info("Before linking: {} to {}", user.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleUserPostCreate(User user) {
        logger.info("After creating: {}", user.toString());
        user.encodePassword();
        userRepository.save(user);
    }

    @HandleAfterSave
    public void handleUserPostSave(User user) {
        logger.info("After updating: {}", user.toString());
        if (user.isPasswordReset()) {
            user.encodePassword();
        }
        userRepository.save(user);
    }

    @HandleAfterDelete
    public void handleUserPostDelete(User user) {
        logger.info("After deleting: {}", user.toString());
    }

    @HandleAfterLinkSave
    public void handleUserPostLinkSave(User user, Object o) {
        logger.info("After linking: {} to {}", user.toString(), o.toString());
    }
}

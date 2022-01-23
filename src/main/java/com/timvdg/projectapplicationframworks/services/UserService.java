package com.timvdg.projectapplicationframworks.services;

import com.timvdg.projectapplicationframworks.Repositories.UserRepository;
import com.timvdg.projectapplicationframworks.models.CustomUserDetails;
import com.timvdg.projectapplicationframworks.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User getLoggedInUser(Authentication authentication){
        if (authentication == null) return null;
        Object principal = authentication.getPrincipal();

        return ((CustomUserDetails) principal).getUser();

    }
}

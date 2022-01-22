package com.timvdg.projectapplicationframworks.controllers;

import com.timvdg.projectapplicationframworks.Repositories.UserRepository;
import com.timvdg.projectapplicationframworks.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping({"/","/home"})
    public String home(){
        return "home";
    }

    @GetMapping({"/register"})
    public String getSignupForm(Model model){
        model.addAttribute("user",new User());
        return "signUpForm";
    }
    @GetMapping({"/productList"})
    public String getProductList(){
        return "productList";
    }
    @PostMapping({"/registerUser"})
    public String registerUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepo.save(user);
        return "registerSucces";
    }
}

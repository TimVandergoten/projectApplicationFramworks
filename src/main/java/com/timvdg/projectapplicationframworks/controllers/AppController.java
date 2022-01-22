package com.timvdg.projectapplicationframworks.controllers;

import com.timvdg.projectapplicationframworks.Repositories.UserRepository;
import com.timvdg.projectapplicationframworks.models.User;
import com.timvdg.projectapplicationframworks.services.CategoryService;
import com.timvdg.projectapplicationframworks.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

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
    public String getProductList(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "productList";
    }
    @GetMapping("/productList/category/{id}")
    public String prodByCat(@PathVariable long id, Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "productList";
    }

    @GetMapping("/productList/viewproduct/{id}")
    public String viewProductById(@PathVariable long id, Model model){
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
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

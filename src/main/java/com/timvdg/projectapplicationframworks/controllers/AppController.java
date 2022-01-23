package com.timvdg.projectapplicationframworks.controllers;

import com.timvdg.projectapplicationframworks.Repositories.UserRepository;
import com.timvdg.projectapplicationframworks.models.CartItem;
import com.timvdg.projectapplicationframworks.models.CustomUserDetails;
import com.timvdg.projectapplicationframworks.models.User;
import com.timvdg.projectapplicationframworks.services.CartServices;
import com.timvdg.projectapplicationframworks.services.CategoryService;
import com.timvdg.projectapplicationframworks.services.ProductService;
import com.timvdg.projectapplicationframworks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CartServices cartServices;
    @Autowired
    UserService userService;

    @GetMapping({"/register"})
    public String getSignupForm(Model model){
        model.addAttribute("user",new User());
        return "signUpForm";
    }
    @GetMapping({"/","/productList"})
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
    @GetMapping("/cart")
    public String openCart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        List<CartItem> itemList = cartServices.listCartItems(user);

        model.addAttribute("itemList",itemList);
        model.addAttribute("totalAmount",0);
        return "userCart";
    }

    @PostMapping({"/registerUser"})
    public String registerUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepo.save(user);
        return "registerSucces";
    }
    @GetMapping("/checkout")
    public String checkout (Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        List<CartItem> itemList = cartServices.listCartItems(user);
        AtomicReference<Float> total = new AtomicReference<>(0f);
        itemList.forEach(cartItem -> {
            double price = cartItem.getProduct().getPrice();
            int amount = cartItem.getQuantity();
            total.updateAndGet(v -> new Float((float) (v + price * amount)));
        });
        model.addAttribute("total",total.get());
        return "checkout";
    }



}

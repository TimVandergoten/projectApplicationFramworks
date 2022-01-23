package com.timvdg.projectapplicationframworks.controllers;

import com.timvdg.projectapplicationframworks.models.CustomUserDetails;
import com.timvdg.projectapplicationframworks.models.User;
import com.timvdg.projectapplicationframworks.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {
    @Autowired
    CartServices cartServices;

    @PostMapping({"/cart/add/{prodid}/{amount}"})
    public String addProdToCart(@PathVariable("prodid") long productId, @PathVariable("amount") int amount, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null){
            return "you must login to perform this action.";
        }
        User user = customUserDetails.getUser();
        if (user == null){
            return "you must login to perform this action.";
        }
        int addedAmount = cartServices.addProduct(productId,amount,user);
        return addedAmount+" of this item is now present in your cart.";
    }
    @PostMapping({"/cart/update/{prodid}/{amount}"})
    public String updateAmount(@PathVariable("prodid") long productId, @PathVariable("amount") int amount, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null){
            return "you must login to perform this action.";
        }
        User user = customUserDetails.getUser();
        if (user == null){
            return "you must login to perform this action.";
        }
        float addedAmount = cartServices.updateAmount(amount,productId,user);
        return String.valueOf(addedAmount);
    }
    @PostMapping({"/cart/remove/{prodid}"})
    public String removeItem(@PathVariable("prodid") long productId, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null){
            return "you must login to perform this action.";
        }
        User user = customUserDetails.getUser();
        if (user == null){
            return "you must login to perform this action.";
        }
        cartServices.removeItem(user,productId);
        return "Item removed";
    }

}

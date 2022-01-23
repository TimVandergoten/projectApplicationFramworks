package com.timvdg.projectapplicationframworks.services;

import com.timvdg.projectapplicationframworks.Repositories.CartItemRepository;
import com.timvdg.projectapplicationframworks.Repositories.ProductRepository;
import com.timvdg.projectapplicationframworks.models.CartItem;
import com.timvdg.projectapplicationframworks.models.Product;
import com.timvdg.projectapplicationframworks.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartServices {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> listCartItems(User user){
        return cartItemRepository.findByUser(user);
    }
    public int addProduct(long productId,int amount,User user){
        int addedAmount = amount;
        Product product = productRepository.findById(productId).get();

        CartItem cartItem = cartItemRepository.findByUserAndAndProduct(user,product);
        if (cartItem != null){
            addedAmount = cartItem.getQuantity()+1;
            cartItem.setQuantity(addedAmount);
        }else{
            cartItem =new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(addedAmount);
        }
        cartItemRepository.save(cartItem);
        return addedAmount;
    }
    public float updateAmount(int amount, long prodId, User user){
        long userId = user.getId();
        cartItemRepository.updateAmount(amount,prodId,userId);
        Product product = productRepository.findById(prodId).get();
        float totalPriceItem = (float) product.getPrice() * amount;
        return totalPriceItem;
    }

    public void removeItem(User user, long prodId){
        cartItemRepository.deleteByUserAndProduct(user.getId(),prodId);
    }
}

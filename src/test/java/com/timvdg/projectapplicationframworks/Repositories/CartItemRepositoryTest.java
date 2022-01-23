package com.timvdg.projectapplicationframworks.Repositories;

import com.timvdg.projectapplicationframworks.models.CartItem;
import com.timvdg.projectapplicationframworks.models.Product;
import com.timvdg.projectapplicationframworks.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class CartItemRepositoryTest {
    @Autowired
    CartItemRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAddOneItem(){
        Product product = testEntityManager.find(Product.class,1L);
        User user = testEntityManager.find(User.class,1L);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(1);

        CartItem savedCartITem = repository.save(cartItem);

        CartItem existedCartItem = testEntityManager.find(CartItem.class,savedCartITem.getId());
        assertThat(existedCartItem.getProduct()).isEqualTo(cartItem.getProduct());
    }
    @Test
    public void testFindByUser(){
        User user = testEntityManager.find(User.class,1L);

        List<CartItem> items = repository.findByUser(user);

        assertEquals(2,items.size());
    }


}
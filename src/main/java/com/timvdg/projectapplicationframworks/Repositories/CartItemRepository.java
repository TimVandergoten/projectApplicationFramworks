package com.timvdg.projectapplicationframworks.Repositories;

import com.timvdg.projectapplicationframworks.models.CartItem;
import com.timvdg.projectapplicationframworks.models.Category;
import com.timvdg.projectapplicationframworks.models.Product;
import com.timvdg.projectapplicationframworks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
   public List<CartItem> findByUser(User user);

   public CartItem findByUserAndAndProduct(User user, Product product);

   @Query("update CartItem c set c.quantity = ?1 where c.product.id = ?2 and c.user.id = ?3")
   @Modifying
   public void updateAmount(int amount,long prodId,long userId);

   @Query("delete from CartItem c where c.user.id = ?1 and  c.product.id= ?2")
   @Modifying
   public void deleteByUserAndProduct(long userId,long prodId);
}

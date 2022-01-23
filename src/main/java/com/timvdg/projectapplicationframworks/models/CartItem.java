package com.timvdg.projectapplicationframworks.models;

import javax.persistence.*;

@Entity
@Table(name = "cartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="productId",referencedColumnName = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private User user;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    public float getTotalPrice(){
     return (float) (this.product.getPrice() * quantity);
    }
}

package com.example.examen.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cart;

    private int quantity;

    @OneToOne()
    @JoinColumn(name = "id_producto")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public long getId_cart() {
        return id_cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public void setId_cart(long id_cart) {
        this.id_cart = id_cart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

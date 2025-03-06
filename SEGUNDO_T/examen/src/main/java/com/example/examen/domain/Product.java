package com.example.examen.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "product")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_product;

    private String name;

    private String descrip;

    private String image_url;

    private double price;

    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_categoria", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private Cart cart;

    public long getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

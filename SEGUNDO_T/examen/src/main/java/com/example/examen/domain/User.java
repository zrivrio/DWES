package com.example.examen.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id_user;

    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Cart> cat_item;

    public long getId_user() {
        return id_user;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Cart> getCat_item() {
        return cat_item;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCat_item(List<Cart> cat_item) {
        this.cat_item = cat_item;
    }
}

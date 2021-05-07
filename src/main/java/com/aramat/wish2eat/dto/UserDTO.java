package com.aramat.wish2eat.dto;

import com.aramat.wish2eat.entities.Product;
import java.util.Set;
import javax.validation.constraints.NotNull;

public class UserDTO {
    private Long id;

    @NotNull
    private String nome;

    Set<Product> addedProducts;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Product> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(Set<Product> addedProducts) {
        this.addedProducts = addedProducts;
    }
}

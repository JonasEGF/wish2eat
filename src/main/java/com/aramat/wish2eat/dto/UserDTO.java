package com.aramat.wish2eat.dto;

import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotNull;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 5494145813754202454L;

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    private Set<ProductDTO> addedProducts;

    private Set<StoreDTO> addStores;

    public UserDTO(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UserDTO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ProductDTO> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(Set<ProductDTO> addedProducts) {
        this.addedProducts = addedProducts;
    }

    public Set<StoreDTO> getAddStores() {
        return addStores;
    }

    public void setAddStores(Set<StoreDTO> addStores) {
        this.addStores = addStores;
    }
}

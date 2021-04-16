package com.aramat.wish2eat.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class StoreDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cep;
    @NotNull
    private String number;
    @NotNull
    private String instagram;
    @NotNull
    private String facebook;
    private Set<ProductDTO> products = new HashSet<>();

    public StoreDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}

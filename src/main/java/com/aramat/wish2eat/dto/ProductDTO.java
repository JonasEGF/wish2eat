package com.aramat.wish2eat.dto;

import javax.validation.constraints.NotNull;

public class ProductDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double value;
    @NotNull
    private String description;

    private Long storeID;

    public ProductDTO() {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStoreID() {
        return storeID;
    }

    public void setStoreID(Long storeID) {
        this.storeID = storeID;
    }
}

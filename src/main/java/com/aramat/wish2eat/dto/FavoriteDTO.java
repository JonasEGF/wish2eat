package com.aramat.wish2eat.dto;

import javax.validation.constraints.NotNull;

public class FavoriteDTO {
    @NotNull
    private Long user;

    @NotNull
    private Long product;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}

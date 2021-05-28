package com.aramat.wish2eat.dto;

import javax.validation.constraints.NotNull;

public class FavStoreDTO {
    @NotNull
    private Long user;

    @NotNull
    private Long store;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getStore() {
        return store;
    }

    public void setStore(Long store) {
        this.store = store;
    }
}

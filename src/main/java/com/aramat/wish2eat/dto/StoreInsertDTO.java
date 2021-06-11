package com.aramat.wish2eat.dto;

import javax.validation.constraints.NotBlank;

public class StoreInsertDTO extends StoreDTO{

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

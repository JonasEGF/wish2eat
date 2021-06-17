package com.aramat.wish2eat.dto;

import com.aramat.wish2eat.service.validation.StoreInsertValid;

import javax.validation.constraints.NotBlank;

@StoreInsertValid
public class StoreInsertDTO extends StoreDTO {

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

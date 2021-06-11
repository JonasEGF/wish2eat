package com.aramat.wish2eat.dto;

import com.aramat.wish2eat.service.validation.UserInsertValid;

import javax.validation.constraints.NotBlank;

@UserInsertValid
public class UserInsertDTO extends UserDTO {
    private static final long serialVersionUID = 4224373440302738839L;

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

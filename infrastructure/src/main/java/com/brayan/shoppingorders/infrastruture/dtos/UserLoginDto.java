package com.brayan.shoppingorders.infrastruture.dtos;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}

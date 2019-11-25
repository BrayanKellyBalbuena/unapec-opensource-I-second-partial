package com.brayan.shoppingorders.infrastruture.dtos;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class UserDto extends BaseDto<Long> {
    private String firstName;
    private String lastName;
    private String identificationCard;
    private String email;
    private String password;
}

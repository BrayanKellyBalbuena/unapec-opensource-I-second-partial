package com.brayan.shoppingorders.infrastruture.dtos;

import lombok.Data;

@Data
public class LocationDto extends BaseDto<Long> {
    private String name;
    private double latitude;
    private double longitude;
}

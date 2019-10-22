package edu.unapec.shoppingorders.dtos;

import lombok.Data;

@Data
public class LocationDto extends BaseDto<Long> {
    private String name;
    private double latitude;
    private double longitude;
}

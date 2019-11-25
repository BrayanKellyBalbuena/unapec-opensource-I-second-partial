package com.brayan.shoppingorders.infrastruture.dtos;

import lombok.Data;

@Data
public class ProductDto extends BaseDto<Long> {
    private Long categoryId;
    private String name;
    private Double price;
    private String category;
}

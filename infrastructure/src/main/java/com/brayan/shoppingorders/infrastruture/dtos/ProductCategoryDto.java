package com.brayan.shoppingorders.infrastruture.dtos;

import lombok.Data;

@Data
public class ProductCategoryDto extends BaseDto<Long> {
    private String name;
    private String description;
}

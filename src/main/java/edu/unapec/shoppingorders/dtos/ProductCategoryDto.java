package edu.unapec.shoppingorders.dtos;

import lombok.Data;

@Data
public class ProductCategoryDto extends BaseDto<Long> {
    private String name;
    private String description;
}

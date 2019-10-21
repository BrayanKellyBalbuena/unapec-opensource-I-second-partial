package edu.unapec.shoppingorders.models;

import lombok.Data;

@Data
public class ProductCategory extends  BaseModel<Long> {
    private String name;
    private String description;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

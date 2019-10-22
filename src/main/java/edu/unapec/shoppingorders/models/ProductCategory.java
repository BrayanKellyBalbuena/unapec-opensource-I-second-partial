package edu.unapec.shoppingorders.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Data
@Entity()
@Table(name = "products_category")
public class ProductCategory extends  BaseModel<Long> {
    @Column(nullable = false, precision = 64)
    private String name;
    @Column(nullable = false, precision = 128)
    private String description;

    public ProductCategory() {
    }

    public ProductCategory(String name, String description, String createdBy) {
        super(createdBy);
        this.name = name;
        this.description = description;
    }

    public ProductCategory(String name, String description, String createdBy,
                           Date modifiedDate, String modifiedBy) {
        super(createdBy, modifiedDate, modifiedBy);
        this.name = name;
        this.description = description;
    }
}

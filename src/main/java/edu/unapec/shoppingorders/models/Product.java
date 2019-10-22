package edu.unapec.shoppingorders.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity()
@Table(name = "products")
public class Product extends BaseModel<Long> {
    @Column(precision = 128, nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private ProductCategory productCategory;

}

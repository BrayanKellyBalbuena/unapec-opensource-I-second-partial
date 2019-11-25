package com.brayan.shoppingorders.core.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private ProductCategory productCategory;

}

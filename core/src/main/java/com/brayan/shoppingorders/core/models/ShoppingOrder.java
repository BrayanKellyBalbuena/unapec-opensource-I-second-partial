package com.brayan.shoppingorders.core.models;

import lombok.Data;
import org.hibernate.annotations.NamedQuery;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement
@Entity
@Table(name = "orders")
@NamedQuery(name = "getOrderByUserId", query = "from ShoppingOrder where userId = :userId and state = 1")
public class ShoppingOrder extends BaseModel<Long> {

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "location_id", nullable = false)
    private Long locationId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    private double price;
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    private int quantity;
    @Column(name = "sub_total")
    private double subTotal;
}

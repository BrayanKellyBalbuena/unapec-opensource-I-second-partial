package com.brayan.shoppingorders.core.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@Entity
@Table(name = "locations")
public class Location extends BaseModel<Long> {
    @Column(nullable = false, precision = 120, unique = true)
    private String name;
    private double latitude;
    private double longitude;
}

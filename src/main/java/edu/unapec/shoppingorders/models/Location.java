package edu.unapec.shoppingorders.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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

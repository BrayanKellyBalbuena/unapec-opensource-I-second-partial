package edu.unapec.shoppingorders.models;

import lombok.Data;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ReportOrders")
public class ReportOrderUser implements Serializable {
    @Id
    private Long id;
    private String fullName;
    private Double totalOrder;
}

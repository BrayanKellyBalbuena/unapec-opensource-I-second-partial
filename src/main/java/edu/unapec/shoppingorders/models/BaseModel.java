package edu.unapec.shoppingorders.models;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel<ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
    protected Date createdDate;
    protected String createdBy;
    protected Date modifiedDate;
    protected String modifiedBy;
}

package edu.unapec.shoppingorders.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@MappedSuperclass
@Data
@XmlRootElement
public abstract class BaseModel<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    @Column(name = "created_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected Date createdDate;

    @Column(name = "created_by")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected String createdBy;

    @Column(name = "modified_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected Date modifiedDate;

    @Column(name = "modified_by")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected String modifiedBy;

    @Column(nullable = false)
    protected boolean state;

    protected BaseModel() {

    }

    protected BaseModel(String createdBy) {
        this.createdBy = createdBy;
    }

    public BaseModel(String createdBy, Date modifiedDate, String modifiedBy) {
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public BaseModel(Date modifiedDate, String modifiedBy) {
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }
}

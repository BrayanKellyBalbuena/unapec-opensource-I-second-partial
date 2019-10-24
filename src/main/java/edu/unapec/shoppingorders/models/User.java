package edu.unapec.shoppingorders.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Data()
@XmlRootElement
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseModel<Long> {
    @Column(name = "first_name", precision = 64, nullable = false)
    private String firstName;
    @Column(name = "last_name", precision = 128, nullable = false)
   private String lastName;
    @Column(name = "identification_card", columnDefinition = "char", precision = 11, nullable = false)

    private String identificationCard;
    @Column(precision = 64, nullable = false)
    private String email;
    @Column(precision = 64, nullable = false)
    private String password;

    public User() {
    }
}

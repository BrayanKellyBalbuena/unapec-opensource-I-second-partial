package edu.unapec.shoppingorders.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;

@Data()
@XmlRootElement
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseModel{

   private String name;
   private String lastName;
   private String identificationCard;

}

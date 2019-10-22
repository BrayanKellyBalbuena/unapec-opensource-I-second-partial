package edu.unapec.shoppingorders.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data()
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseModel{

   private String name;
   private String lastName;
   private String identificationCard;

}

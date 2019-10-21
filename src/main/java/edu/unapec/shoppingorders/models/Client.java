package edu.unapec.shoppingorders.models;

import lombok.Data;

import java.util.Date;

@Data()
public class Client extends BaseModel{

   private String name;
   private String lastName;
   private String identificationCard;

    public Client(Long id, String name, String lastName, String identificationCard, Date createdDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identificationCard = identificationCard;
        this.createdDate = createdDate;

    }


}

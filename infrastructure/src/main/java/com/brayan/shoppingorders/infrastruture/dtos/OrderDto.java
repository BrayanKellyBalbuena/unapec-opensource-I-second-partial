package com.brayan.shoppingorders.infrastruture.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement
public class OrderDto extends BaseDto<Long> {
    private Long userId;
    private String userName;
    private LocationDto location;
    private ProductDto product;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date orderDate;
    private double price;
    private int quantity;
    private double subTotal;
}

package com.brayan.shoppingorders.core.models;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class ReportOrderDate {
    private String orderDate;
    private Long quantity;
}

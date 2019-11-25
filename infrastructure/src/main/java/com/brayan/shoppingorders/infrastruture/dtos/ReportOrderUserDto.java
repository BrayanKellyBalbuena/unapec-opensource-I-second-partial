package com.brayan.shoppingorders.infrastruture.dtos;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Entity;
import java.io.Serializable;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "callGetAllFoos",
                query = "CALL GetAllFoos()",
                resultClass = ReportOrderUserDto.class)
})
@Entity
public class ReportOrderUserDto implements Serializable {
}

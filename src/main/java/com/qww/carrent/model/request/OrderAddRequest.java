package com.qww.carrent.model.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderAddRequest implements Serializable {

    /**
     * 汽车id
     */
    private Integer carId;

    /**
     * 租赁开始时间
     */
    private Date rentalStartDate;

    /**
     * 租赁结束时间
     */
    private Date rentalEndDate;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 家庭地址
     */
    private String address;

    private static final long serialVersionUID = 1L;

}

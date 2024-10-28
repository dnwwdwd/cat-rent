package com.qww.carrent.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderAddRequest {

    /**
     * id
     */
    private Integer id;

    /**
     * 汽车详情id
     */
    private Integer carDetailId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 租赁开始时间
     */
    private Date rentalStartDate;

    /**
     * 租赁结束时间
     */
    private Date rentalEndDate;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

}

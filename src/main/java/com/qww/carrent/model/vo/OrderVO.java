package com.qww.carrent.model.vo;

import com.qww.carrent.model.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVO implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 汽车详情id
     */
    private Integer carId;

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
    private String rentalStartDate;

    /**
     * 租赁结束时间
     */
    private String rentalEndDate;

    /**
     * 状态
     */
    private Integer status;

    private CarVO carVO;

    private User user;

    private static final long serialVersionUID = 1L;

}

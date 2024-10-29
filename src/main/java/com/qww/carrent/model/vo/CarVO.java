package com.qww.carrent.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CarVO implements Serializable {

    private Integer id;

    private Integer carCategoryId;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 行驶里程（单位：km）
     */
    private Integer mileage;

    /**
     * 成色
     */
    private String quality;

    /**
     * 图片链接
     */
    private String imgUrl;

    /**
     * 单日租车价格
     */
    private BigDecimal rentPrice;

    /**
     * 颜色
     */
    private String color;

    /**
     * 状态（0 - 未出租 1 - 已出租）
     */
    private Integer status;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 车型
     */
    private String type;

    /**
     * 库存数量
     */
    private Integer stock;

    private static final long serialVersionUID = 1L;
}

package com.qww.carrent.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarCategoryAddRequest implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 车型
     */
    private String type;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 库存数量
     */
    private Integer stock;

    private static final long serialVersionUID = 1L;

}

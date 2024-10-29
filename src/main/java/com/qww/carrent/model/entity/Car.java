package com.qww.carrent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 汽车详情表
 * @TableName car
 */
@TableName(value ="car")
@Data
public class Car implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.qww.carrent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 汽车表
 * @TableName car_category
 */
@TableName(value ="car_category")
@Data
public class CarCategory implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（0 - 否 1 - 是）
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
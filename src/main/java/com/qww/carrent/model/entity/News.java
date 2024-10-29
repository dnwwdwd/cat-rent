package com.qww.carrent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资讯表
 * @TableName news
 */
@TableName(value ="news")
@Data
public class News implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 资讯标题
     */
    private String title;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 资讯描述
     */
    private String description;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
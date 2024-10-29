package com.qww.carrent.model.vo;

import com.qww.carrent.model.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class NewsVO implements Serializable {

    private Integer id;

    /**
     * 活动名称
     */
    private String name;

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

    private User user;

}

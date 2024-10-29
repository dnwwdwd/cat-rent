package com.qww.carrent.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *

 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    private String userPassword;

    /**
     * 用户头像
     */
    private String userAvatar;


    private String phone;

    private String address;

    private static final long serialVersionUID = 1L;
}
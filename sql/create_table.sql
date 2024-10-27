-- 创建库
create database if not exists car_rent;

-- 切换库
use car_rent;

-- 用户登录/注册/介绍表
create table car_rent.user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    address      varchar(64)                            null comment '家庭住址',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
)
    comment '用户登录/注册/介绍表' collate = utf8mb4_unicode_ci;


create table car_rent.car
(
    id         int auto_increment comment 'id'
        primary key,
    brand      varchar(128)                       null comment '品牌',
    type       varchar(128)                       null comment '车型',
    imgUrl     varchar(256)                       null comment '图片',
    stock      int                                null comment '库存数量',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除（0 - 否 1 - 是）'
)
    comment '汽车表';


create table car_rent.car_detail
(
    id        int auto_increment comment 'id'
        primary key,
    carNumber varchar(32)       null comment '车牌号',
    mileage   int               null comment '行驶里程（单位：km）',
    quality   varchar(32)       null comment '成色',
    rentPrice decimal(10, 2)    null comment '单日租车价格',
    color     varchar(16)       null comment '颜色',
    status    tinyint default 0 not null comment '状态（0 - 未出租 1 - 已出租）'
)
    comment '汽车详情表';


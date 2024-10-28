package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.mapper.OrderMapper;
import com.qww.carrent.model.entity.Order;
import com.qww.carrent.service.OrderService;
import org.springframework.stereotype.Service;

/**
* @author hejiajun
* @description 针对表【order(订单表)】的数据库操作Service实现
* @createDate 2024-10-27 22:23:35
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}





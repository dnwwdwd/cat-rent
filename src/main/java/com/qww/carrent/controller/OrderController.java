package com.qww.carrent.controller;

import com.qww.carrent.annotation.AuthCheck;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.constant.UserConstant;
import com.qww.carrent.model.entity.Order;
import com.qww.carrent.model.entity.User;
import com.qww.carrent.model.request.OrderAddRequest;
import com.qww.carrent.service.CarService;
import com.qww.carrent.service.OrderService;
import com.qww.carrent.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    @Resource
    private CarService carService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addOrder(@RequestBody OrderAddRequest orderAddRequest,
                                          HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        Car car = carService.getById(orderAddRequest.getCarDetailId());
        Date rentalEndDate = orderAddRequest.getRentalEndDate();
        Date rentalStartDate = orderAddRequest.getRentalStartDate();
        long diffInMillies = rentalEndDate.getTime() - rentalStartDate.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies);
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(car.getRentPrice().multiply(BigDecimal.valueOf(diffInDays)));
        BeanUtils.copyProperties(orderAddRequest, order);
        boolean b = orderService.save(order);
        return ResultUtils.success(b);
    }

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateOrder(@RequestBody Order order) {
        boolean b = orderService.updateById(order);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<Order>> listOrders() {
        List<Order> orderList = orderService.list();
        return ResultUtils.success(orderList);
    }

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteOrder(@RequestBody DeleteRequest deleteRequest) {
        Long id = deleteRequest.getId();
        boolean b = orderService.removeById(id);
        return ResultUtils.success(b);
    }

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/process")
    public BaseResponse<Boolean> processOrder(@RequestBody DeleteRequest deleteRequest) {
        Long id = deleteRequest.getId();
        Order order = orderService.getById(id);
        order.setStatus(1);
        boolean b = orderService.updateById(order);
        return ResultUtils.success(b);

    }

}

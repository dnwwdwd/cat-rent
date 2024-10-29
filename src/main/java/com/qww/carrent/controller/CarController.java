package com.qww.carrent.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qww.carrent.annotation.AuthCheck;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.constant.UserConstant;
import com.qww.carrent.model.entity.Car;
import com.qww.carrent.model.request.CarAddRequest;
import com.qww.carrent.model.vo.CarVO;
import com.qww.carrent.service.CarCategoryService;
import com.qww.carrent.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {

    @Resource
    private CarService carService;

    @Resource
    private CarCategoryService carCategoryService;


    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/add")
    public BaseResponse<Boolean> addCar(@RequestBody CarAddRequest carAddRequest) {
        Car car = new Car();
        BeanUtils.copyProperties(carAddRequest, car);
        boolean b = carService.save(car);
        return ResultUtils.success(b);
    }


    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateCar(@RequestBody Car car) {
        boolean b = carService.updateById(car);
        return ResultUtils.success(b);
    }

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCar(@RequestBody DeleteRequest deleteRequest) {
        Integer id = deleteRequest.getId();
        boolean b = carService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<Car>> listCars() {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        List<Car> carList = carService.list(queryWrapper);
        return ResultUtils.success(carList);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<CarVO> detail(@PathVariable Integer id) {
        CarVO carVO = carService.getCarDetailById(id);
        return ResultUtils.success(carVO);
    }

    @GetMapping("/list/{id}")
    public BaseResponse<List<CarVO>> listCarsByCategoryId(@PathVariable Integer id) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("carCategoryId", id);
        List<Car> carList = carService.list(queryWrapper);
        List<CarVO> carVOList = carList.stream().map(car ->
                carService.getCarDetailById(car.getId())).collect(Collectors.toList());
        return ResultUtils.success(carVOList);
    }

    @GetMapping("/list/id")
    public BaseResponse<List<Integer>> listCarIds() {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        List<Integer> ids = carService.list(queryWrapper).stream().map(Car::getId).collect(Collectors.toList());
        return ResultUtils.success(ids);
    }
}

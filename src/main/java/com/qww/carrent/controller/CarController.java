package com.qww.carrent.controller;

import com.qww.carrent.annotation.AuthCheck;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.constant.UserConstant;
import com.qww.carrent.model.entity.Car;
import com.qww.carrent.model.entity.CarCategory;
import com.qww.carrent.model.request.CarAddRequest;
import com.qww.carrent.model.vo.CarVO;
import com.qww.carrent.service.CarCategoryService;
import com.qww.carrent.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        Long id = deleteRequest.getId();
        boolean b = carService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<Car>> listCars() {
        List<Car> carList = carService.list();
        return ResultUtils.success(carList);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<CarVO> detail(@PathVariable Integer id) {
        CarVO carVO = carService.getCarDetailById(id);
        return ResultUtils.success(carVO);
    }
}

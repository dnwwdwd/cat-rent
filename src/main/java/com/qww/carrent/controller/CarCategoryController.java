package com.qww.carrent.controller;

import com.qww.carrent.annotation.AuthCheck;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.constant.UserConstant;
import com.qww.carrent.model.entity.CarCategory;
import com.qww.carrent.model.request.CarCategoryAddRequest;
import com.qww.carrent.service.CarCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/car_category")
public class CarCategoryController {

    @Resource
    private CarCategoryService carCategoryService;

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/add")
    public BaseResponse<Boolean> addCarCategory(@RequestBody CarCategoryAddRequest carCategoryAddRequest) {
        CarCategory carCategory = new CarCategory();
        BeanUtils.copyProperties(carCategoryAddRequest, carCategory);
        boolean b = carCategoryService.save(carCategory);
        return ResultUtils.success(b);

    }

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateCarCategory(@RequestBody CarCategory carCategory) {
        boolean b = carCategoryService.updateById(carCategory);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<CarCategory>> listCarCategories() {
        List<CarCategory> carCategoryList = carCategoryService.list();
        return ResultUtils.success(carCategoryList);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCarCategory(@RequestBody DeleteRequest deleteRequest) {
        Long id = deleteRequest.getId();
        boolean b = carCategoryService.removeById(id);
        return ResultUtils.success(b);
    }

}

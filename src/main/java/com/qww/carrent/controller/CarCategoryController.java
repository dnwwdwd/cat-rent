package com.qww.carrent.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qww.carrent.annotation.AuthCheck;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.constant.UserConstant;
import com.qww.carrent.model.entity.CarCategory;
import com.qww.carrent.model.request.CarCategoryAddRequest;
import com.qww.carrent.model.request.CarCategoryQueryRequest;
import com.qww.carrent.service.CarCategoryService;
import com.qww.carrent.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car_category")
public class CarCategoryController {

    @Resource
    private CarCategoryService carCategoryService;

    @Resource
    private CarService carService;

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

    @PostMapping("/list")
    public BaseResponse<List<CarCategory>> listCarCategories(@RequestBody CarCategoryQueryRequest
                                                                     carCategoryQueryRequest) {
        String searchText = carCategoryQueryRequest.getSearchText();
        Integer minPrice = carCategoryQueryRequest.getMinPrice();
        Integer maxPrice = carCategoryQueryRequest.getMaxPrice();
        QueryWrapper<CarCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(searchText), "brand", searchText);
        queryWrapper.or().like(StringUtils.isNotEmpty(searchText), "type", searchText);
        queryWrapper.between(minPrice != null && maxPrice != null, "averagePrice", minPrice, maxPrice);
        queryWrapper.gt("stock", 0);
        List<CarCategory> carCategoryList = carCategoryService.list(queryWrapper);
        return ResultUtils.success(carCategoryList);
    }

    @GetMapping("/list")
    public BaseResponse<List<CarCategory>> listCarCategoriesByGet() {
        List<CarCategory> carCategoryList = carCategoryService.list();
        return ResultUtils.success(carCategoryList);
    }


    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCarCategory(@RequestBody DeleteRequest deleteRequest) {
        Integer id = deleteRequest.getId();
        boolean b = carCategoryService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping("/list/id")
    public BaseResponse<List<Integer>> listCarCategoryIds() {
        List<Integer> idList = carCategoryService.list().stream().map(CarCategory::getId).collect(Collectors.toList());
        return ResultUtils.success(idList);
    }
}

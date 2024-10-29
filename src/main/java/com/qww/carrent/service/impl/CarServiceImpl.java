package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.mapper.CarMapper;
import com.qww.carrent.model.entity.Car;
import com.qww.carrent.model.entity.CarCategory;
import com.qww.carrent.model.vo.CarVO;
import com.qww.carrent.service.CarCategoryService;
import com.qww.carrent.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author hejiajun
* @description 针对表【car_detail(汽车详情表)】的数据库操作Service实现
* @createDate 2024-10-27 22:23:29
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car>
    implements CarService {

    @Resource
    private CarCategoryService carCategoryService;

    @Override
    public CarVO getCarDetailById(Integer id) {
        Car car = this.getById(id);
        CarVO carVO = new CarVO();
        CarCategory carCategory = carCategoryService.getById(car.getCarCategoryId());
        BeanUtils.copyProperties(carCategory, carVO);
        BeanUtils.copyProperties(car, carVO);
        return carVO;
    }
}





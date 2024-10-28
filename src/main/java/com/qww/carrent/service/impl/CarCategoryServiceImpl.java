package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.mapper.CarCategoryMapper;
import com.qww.carrent.model.entity.CarCategory;
import com.qww.carrent.service.CarCategoryService;
import org.springframework.stereotype.Service;

/**
* @author hejiajun
* @description 针对表【car_category(汽车表)】的数据库操作Service实现
* @createDate 2024-10-28 10:20:59
*/
@Service
public class CarCategoryServiceImpl extends ServiceImpl<CarCategoryMapper, CarCategory>
    implements CarCategoryService{

}





package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.model.entity.Car;
import com.qww.carrent.service.CarService;
import com.qww.carrent.mapper.CarMapper;
import org.springframework.stereotype.Service;

/**
* @author hejiajun
* @description 针对表【car(汽车表)】的数据库操作Service实现
* @createDate 2024-10-27 22:23:03
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car>
    implements CarService{

}




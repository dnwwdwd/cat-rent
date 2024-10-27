package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.model.entity.CarDetail;
import com.qww.carrent.service.CarDetailService;
import com.qww.carrent.mapper.CarDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author hejiajun
* @description 针对表【car_detail(汽车详情表)】的数据库操作Service实现
* @createDate 2024-10-27 22:23:29
*/
@Service
public class CarDetailServiceImpl extends ServiceImpl<CarDetailMapper, CarDetail>
    implements CarDetailService{

}





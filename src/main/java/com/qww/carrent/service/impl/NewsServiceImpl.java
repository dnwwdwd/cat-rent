package com.qww.carrent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qww.carrent.model.entity.News;
import com.qww.carrent.service.NewsService;
import com.qww.carrent.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author hejiajun
* @description 针对表【news(资讯表)】的数据库操作Service实现
* @createDate 2024-10-29 13:32:08
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}





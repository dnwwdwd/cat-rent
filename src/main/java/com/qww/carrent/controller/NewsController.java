package com.qww.carrent.controller;
import com.qww.carrent.common.BaseResponse;
import com.qww.carrent.common.DeleteRequest;
import com.qww.carrent.common.ErrorCode;
import com.qww.carrent.common.ResultUtils;
import com.qww.carrent.exception.BusinessException;
import com.qww.carrent.model.entity.News;
import com.qww.carrent.model.entity.User;
import com.qww.carrent.model.vo.NewsVO;
import com.qww.carrent.service.NewsService;
import com.qww.carrent.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private UserService userService;

    @Resource
    private NewsService newsService;

    @PostMapping("/add")
    public BaseResponse<Integer> addNews(@RequestBody News news, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        news.setUserId(loginUser.getId());
        newsService.save(news);
        return ResultUtils.success(news.getId());
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateNews(@RequestBody News news, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = newsService.updateById(news);
        return ResultUtils.success(b);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteNews(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        int id = deleteRequest.getId();
        boolean b = newsService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<NewsVO>> listActivities() {
        List<News> activities = newsService.list();
        List<NewsVO> newsVOS = activities.stream().map(news -> {
            NewsVO newsVO = new NewsVO();
            newsVO.setUser(userService.getById(news.getUserId()));
            BeanUtils.copyProperties(news, newsVO);
            return newsVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(newsVOS);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<NewsVO> getNewsDetailById(@PathVariable("id") Integer id) {
        News news = newsService.getById(id);
        NewsVO newsVO = new NewsVO();
        newsVO.setUser(userService.getById(news.getUserId()));
        BeanUtils.copyProperties(news, newsVO);
        return ResultUtils.success(newsVO);
    }

}


package com.finn.controller;

import com.finn.enums.ResultEnums;
import com.finn.service.HomeService;
import com.finn.service.PageService;
import com.finn.utils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * @description:
 * @author: Finn
 * @create: 2022/02/06 20:43
 */
@RestController
@ApiModel(value = "首页信息")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "根据角色获取展示菜单列表")
    @GetMapping("/api/home")
    public Result getHome() {
        return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("homeInfo", homeService.getHomeInfo());
    }
}

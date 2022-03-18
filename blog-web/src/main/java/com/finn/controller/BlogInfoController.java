package com.finn.controller;

import com.finn.enums.ResultEnums;
import com.finn.service.BlogInfoService;
import com.finn.utils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * @description:
 * @author: Finn
 * @create: 2022/02/06 20:43
 */
@RestController
@ApiModel(value = "BlogInfoController")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    @ApiOperation(value = "获取博客信息")
    @GetMapping("/api")
    public Result getBlogInfo() {
        return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("blogInfo", blogInfoService.getBlogInfo());
    }

    @ApiOperation(value = "上传访客信息")
    @PostMapping("/api/report")
    public Result report() {
        blogInfoService.report();
        return Result.success();
    }
}

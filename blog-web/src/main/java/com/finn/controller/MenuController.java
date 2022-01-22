package com.finn.controller;


import com.finn.enums.ResultEnums;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-01-20
 */
@RestController
@Api(tags = "菜单展示模块")
@RequestMapping(value = "/menu")
public class MenuController {

    @GetMapping("/getMenuList") // GetMapping是这样定义的：@RequestMapping( method = {RequestMethod.GET})
    @ApiOperation(value = "获取展示菜单列表")
    public ResultEnums getMenuByRoleName(){

        return null;
    }
}

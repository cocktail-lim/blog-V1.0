package com.finn.controller;


import com.finn.enums.ResultEnums;
import com.finn.service.MenuService;
import com.finn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/api")
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiOperation(value = "根据角色获取展示菜单列表")
    @GetMapping("/admin/getMenus") // GetMapping是这样定义的：@RequestMapping( method = {RequestMethod.GET})
    public Result getMenuByRoleName(@RequestParam String roleName){
        return new Result().success().codeAndMessage(ResultEnums.SUCCESS).data("menuList", menuService.getMenuListByRoleName(roleName));
    }
}

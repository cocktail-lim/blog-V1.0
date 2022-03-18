package com.finn.controller;


import com.finn.dto.MenuDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.MenuService;
import com.finn.utils.Result;
import com.finn.vo.ConditionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-01-20
 */
@RestController
@ApiModel(value = "菜单展示模块")
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiOperation(value = "根据角色获取展示菜单列表")
    @GetMapping("/api/admin/getMenuList") // GetMapping是这样定义的：@RequestMapping( method = {RequestMethod.GET})
    public Result getMenuList(ConditionVO conditionVO){
        return new Result().success().codeAndMessage(ResultEnums.SUCCESS).data("menuList", menuService.getMenuList(conditionVO));
    }
}

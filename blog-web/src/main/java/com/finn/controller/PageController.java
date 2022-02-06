package com.finn.controller;

import com.finn.enums.ResultEnums;
import com.finn.service.PageService;
import com.finn.utils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 页面 前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-02-06
 */
@RestController
@ApiModel(value = "show page 页面")
public class PageController {

    @Autowired
    private PageService pageService;

    @ApiOperation(value = "根据角色获取展示菜单列表")
    @GetMapping("/api/listPages")
    public Result listPages() {
        return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("pageList", pageService.listPages());
    }

}

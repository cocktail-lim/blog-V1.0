package com.finn.controller;


import com.finn.entity.Category;
import com.finn.enums.ResultEnums;
import com.finn.service.CategoryService;
import com.finn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取文章分类")
    @GetMapping("/api/admin/category/getCategory")
    public Result getCategoryList() {
        List<Category> categoryList = categoryService.list();
        if(!categoryList.isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("categoryList", categoryList);
        } else {
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
        }
    }

}

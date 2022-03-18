package com.finn.controller;


import com.finn.entity.Tag;
import com.finn.enums.ResultEnums;
import com.finn.service.TagService;
import com.finn.utils.Result;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取所有Tag")
    @GetMapping("/api/tag/getTag")
    public Result getTagList() {
        List<Tag> tagList = tagService.list();
        if(!tagList.isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("tagList", tagList);
        } else {
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
        }
    }

//    @ApiOperation("根据文章搜索Tag")
//    @GetMapping("/api/admin/getTagsBy")
//    public Result getTagsByArticle() {
//
//    }
}

package com.finn.controller;


import com.finn.service.ArticleService;
import com.finn.utils.Result;
import com.finn.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Controller
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/article/getArticle")
    public Result getArticle(){
        return Result.success();
    }

    @ApiOperation(value = "添加或修改文章")
    @PostMapping("/admin/article/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVO) {
        articleService.saveOrUpdateArticle(articleVO);
        return Result.success();
    }
}

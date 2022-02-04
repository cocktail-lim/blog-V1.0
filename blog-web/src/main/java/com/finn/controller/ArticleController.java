package com.finn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.ArticleListPageDTO;
import com.finn.dto.UserListPageDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.ArticleService;
import com.finn.utils.Result;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
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
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取文章")
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

    @ApiOperation(value = "获取后台文章列表")
    @GetMapping(value = "/admin/article/getArticleListPage")
    public Result getArticleListPage(ArticleListVO articleListVO){
        Page<ArticleListPageDTO> page = new Page<>(articleListVO.getCurrent(), articleListVO.getSize());
        IPage<ArticleListPageDTO> articleListPage = articleService.getArticleListPage(page, articleListVO);
        long total = articleListPage.getTotal();
        List<ArticleListPageDTO> articles = articleListPage.getRecords();
        if(!articles.isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("articleList",articles).data("total", total);
        } else
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
    }

}

package com.finn.controller;


import com.finn.dto.ArticleListPageBackDTO;
import com.finn.dto.PageDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.ArticleService;
import com.finn.utils.Result;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleTopVO;
import com.finn.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取文章")
    @GetMapping(value = "/api/article/getArticle")
    public Result getArticle(){
        return Result.success();
    }

    @ApiOperation(value = "添加或修改文章")
    @PostMapping("/api/admin/article/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVO) {
        articleService.saveOrUpdateArticle(articleVO);
        return Result.success().codeAndMessage(ResultEnums.SUCCESS);
    }

    @ApiOperation(value = "获取后台文章列表")
    @GetMapping(value = "/api/admin/article/listArticleBackPage")
    public Result getArticleListPage(ArticleListVO articleListVO){
        PageDTO<ArticleListPageBackDTO> pageDTO = articleService.listArticlePageBackDTO(articleListVO);
        if(!pageDTO.getRecords().isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("articleList", pageDTO.getRecords()).data("total", pageDTO.getTotal());
        } else
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
    }

    @ApiOperation(value = "根据文章id置顶文章")
    @PostMapping(value = "/api/admin/article/topArticleById")
    public Result topArticleById(@Valid @RequestBody ArticleTopVO articleTopVO) {
        articleService.topArticleById(articleTopVO.getArticleId(), articleTopVO.getIsTop());
        return Result.success().codeAndMessage(ResultEnums.SUCCESS);
    }

    @ApiOperation(value = "获取后台文章总数(包括草稿)")
    @GetMapping(value = "/api/admin/article/countArticleBack")
    public Result countArticleBack() {
        return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("totalArticle", articleService.countArticleBack());
    }
}

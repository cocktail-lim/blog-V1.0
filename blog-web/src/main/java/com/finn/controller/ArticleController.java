package com.finn.controller;


import com.finn.dto.ArticleListPageBackDTO;
import com.finn.dto.ArticlePreviewPageDTO;
import com.finn.entity.IPage;
import com.finn.enums.ResultEnums;
import com.finn.service.ArticleService;
import com.finn.utils.Result;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleTopVO;
import com.finn.vo.ArticleVO;
import com.finn.vo.DeleteVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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
    public Result getArticle() {
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
    public Result getArticleListPage(ArticleListVO articleListVO) {
        IPage<ArticleListPageBackDTO> IPage = articleService.listArticlePageBackDTO(articleListVO);
        if (!IPage.getRecords().isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("articleList", IPage.getRecords()).data("total", IPage.getTotal());
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
        return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("totalArticle", articleService.countArticleBack(false));
    }

    @ApiOperation(value = "逻辑删除和恢复文章")
    @PostMapping(value = "/api/admin/article/recoverOrDeleteArticle")
    public Result recoverOrDeleteArticle(@Valid @RequestBody DeleteVO deleteVO) {
        articleService.recoverOrDeleteArticle(deleteVO);
        return Result.success().codeAndMessage(ResultEnums.SUCCESS);
    }

    @ApiOperation(value = "获取展示页文章列表")
    @GetMapping(value = "/api/article/listArticlePreviewPage")
    public Result listArticlePreviewPage(ArticleListVO articleListVO) {
        IPage<ArticlePreviewPageDTO> IPage = articleService.listArticlePreviewPageDTO(articleListVO);
        if (!IPage.getRecords().isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("articleList", IPage.getRecords()).data("total", IPage.getTotal());
        } else
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("articleList", new ArrayList(0)).data("total", IPage.getTotal());
    }

    @ApiOperation(value = "获取展示页文章内容")
    @GetMapping(value = "/api/article/showArticleContent")
    public Result showArticleContent(@RequestParam Integer articleId) {
        return Result
                .success()
                .codeAndMessage(ResultEnums.SUCCESS)
                .data("articleContent", articleService.showArticleContent(articleId));
    }

}

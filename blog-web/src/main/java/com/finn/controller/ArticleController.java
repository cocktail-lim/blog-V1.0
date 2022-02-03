package com.finn.controller;


import com.finn.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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

    @GetMapping(value = "/article/getArticle")
    public Result getArticle(){
        return Result.success();
    }
}

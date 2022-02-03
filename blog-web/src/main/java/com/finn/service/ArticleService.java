package com.finn.service;

import com.finn.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finn.vo.ArticleVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Service
public interface ArticleService extends IService<Article> {

    /*
    * @Description: 新增博客
    * @Param:
    * @return:
    * @Author: Finn
    * @Date: 2022/02/03 20:13
    */
    void saveOrUpdateArticle(ArticleVO articleVO);
}


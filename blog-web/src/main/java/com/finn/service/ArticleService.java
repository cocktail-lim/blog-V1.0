package com.finn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.ArticleListPageDTO;
import com.finn.dto.UserListPageDTO;
import com.finn.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleVO;
import com.finn.vo.UserQueryVO;
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

    /* 
    * @Description: 获取文章列表 
    * @Param: [page, articleListVO] 
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.finn.dto.ArticleListPageDTO> 
    * @Author: Finn
    * @Date: 2022/02/04 14:51
    */
    IPage<ArticleListPageDTO> getArticleListPage(Page<ArticleListPageDTO> page, ArticleListVO articleListVO);

    /*
    * @Description: 置顶文章
    * @Param: []
    * @return: boolean
    * @Author: Finn
    * @Date: 2022/02/04 21:12
    */
   void topArticleById(Integer articleId, Boolean isTop);
}


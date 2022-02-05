package com.finn.service;

import com.finn.dto.ArticleListPageBackDTO;
import com.finn.dto.PageDTO;
import com.finn.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

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
    * @Description: 获取后台文章总数
    * @Param: [] 
    * @return: long 
    * @Author: Finn
    * @Date: 2022/02/05 19:59
    */
    Long countArticleBack();

    /*
    * @Description: 获取后台文章列表 List
     * @Param: [articleListVO]
    * @return: java.util.List<com.finn.dto.ArticleListPageBackDTO>
    * @Author: Finn
    * @Date: 2022/02/05 20:17
    */
    List<ArticleListPageBackDTO> listArticlePageBack(ArticleListVO articleListVO);

    /*
    * @Description: 获取后台文章列表要返回给前端的数据
    * @Param: [page, articleListVO]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.finn.dto.ArticleListPageBackDTO>
    * @Author: Finn
    * @Date: 2022/02/04 14:51
    */
//    IPage<ArticleListPageBackDTO> getArticleListPage(Page<ArticleListPageBackDTO> page, ArticleListVO articleListVO);
    PageDTO<ArticleListPageBackDTO> listArticlePageBackDTO(ArticleListVO articleListVO);

    /*
    * @Description: 置顶文章
    * @Param: []
    * @return: boolean
    * @Author: Finn
    * @Date: 2022/02/04 21:12
    */
   void topArticleById(Integer articleId, Boolean isTop);
}


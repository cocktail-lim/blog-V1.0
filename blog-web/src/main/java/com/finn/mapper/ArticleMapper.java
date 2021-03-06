package com.finn.mapper;

import com.finn.dto.ArticleDTO;
import com.finn.dto.ArticleListPageBackDTO;
import com.finn.dto.ArticlePreviewPageDTO;
import com.finn.dto.ArticleRecommendDTO;
import com.finn.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finn.vo.ArticleListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /* 
    * @Description: 获取前/后台文章总数 blog页：isShowPage为true， admin：isShowPage为false
    * @Param: [isShowPage]
    * @return: long 
    * @Author: Finn
    * @Date: 2022/02/05 19:59
    */
    Integer countArticleBack(@Param("isShowPage") Boolean isShowPage);

    /*
    * @Description: 根据 articleListVO 获取文章列表
    * @Param: [page, articleListVO]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.finn.dto.ArticleListPageBackDTO>
    * @Author: Finn
    * @Date: 2022/02/04 15:01
    */
    List<ArticleListPageBackDTO> listArticlePageBack(@Param("articleListVO") ArticleListVO articleListVO);

    /*
    * @Description: 根据文章ID置顶文章
    * @Param: [articleId, isTop]
    * @return: void
    * @Author: Finn
    * @Date: 2022/02/04 21:15
    */
    void topArticleById(@Param("articleId") Integer articleId, @Param("isTop") Integer isTop);

    /* 
    * @Description: 获取展示页文章列表
    * @Param: [articleListVO] 
    * @return: java.util.List<com.finn.dto.ArticlePreviewPageDTO> 
    * @Author: Finn
    * @Date: 2022/02/05 21:12
    */
    List<ArticlePreviewPageDTO> listArticlePreview(@Param("articleListVO") ArticleListVO articleListVO);

    /*
    * @Description: 展示文章内容
    * @Param: [articleId]
    * @return: java.util.List<com.finn.dto.ArticleDTO>
    * @Author: Finn
    * @Date: 2022/02/05 21:43
    */
    ArticleDTO getArticleById(@Param("articleId") Integer articleId);

    /* 
    * @Description: 获取推荐文章list
    * @Param: [articleId] 
    * @return: java.util.List<com.finn.dto.ArticleRecommendDTO> 
    * @Author: Finn
    * @Date: 2022/03/07 21:53
    */
    List<ArticleRecommendDTO> listRecommendArticles(Integer articleId);
}

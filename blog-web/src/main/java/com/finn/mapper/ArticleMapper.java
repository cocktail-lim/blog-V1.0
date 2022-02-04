package com.finn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.ArticleListPageDTO;
import com.finn.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    * @Description: 根据 articleListVO 获取文章列表
    * @Param: [page, articleListVO]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.finn.dto.ArticleListPageDTO>
    * @Author: Finn
    * @Date: 2022/02/04 15:01
    */
    IPage<ArticleListPageDTO> getArticleListPage(Page<ArticleListPageDTO> page, @Param("articleListVO") ArticleListVO articleListVO);

    /*
    * @Description: 根据文章ID置顶文章
    * @Param: [articleId, isTop]
    * @return: void
    * @Author: Finn
    * @Date: 2022/02/04 21:15
    */
    void topArticleById(@Param("articleId") Integer articleId, @Param("isTop") Boolean isTop);
}

package com.finn.mapper;

import com.finn.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    void createArticle(@Param("articleVO") ArticleVO articleVO);
}

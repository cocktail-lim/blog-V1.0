package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.finn.entity.Article;
import com.finn.entity.ArticleTag;
import com.finn.entity.Tag;
import com.finn.mapper.ArticleMapper;
import com.finn.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finn.service.ArticleTagService;
import com.finn.service.TagService;
import com.finn.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateArticle(ArticleVO articleVO) {
        Article article = Article.builder()
                .articleId(articleVO.getArticleId())
                .articleTitle(articleVO.getArticleTitle())
                .articleContent(articleVO.getArticleContent())
                .articleCover(articleVO.getArticleCover())
                .categoryId(articleVO.getCategoryId())
                .isTop(articleVO.getIsTop())
                .isDraft(articleVO.getIsDraft())
                .createTime(Objects.isNull(articleVO.getArticleId()) ? new Date() : null)
                .updateTime(Objects.nonNull(articleVO.getArticleId()) ? new Date() : null)
                .build()
                ;
        articleService.saveOrUpdate(article);

        /*如果编辑文章，则删除所有Tag*/
        if(Objects.nonNull(articleVO.getArticleId()) && articleVO.getIsDraft().equals(false)) {
            articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleVO.getArticleId()));
        }
        /*添加tag*/
        if(!articleVO.getTagList().isEmpty()) {
            List<ArticleTag> articleTagList = articleVO.getTagList().stream().map(tagId -> ArticleTag.builder()
                            .id(null)
                            .articleId(article.getArticleId())
                            .tagId(tagId)
                            .build())
                    .collect(Collectors.toList());
            articleTagService.saveBatch(articleTagList);
        }
    }
}

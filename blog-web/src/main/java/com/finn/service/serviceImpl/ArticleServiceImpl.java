package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.finn.dto.*;
import com.finn.entity.IPage;
import com.finn.entity.Article;
import com.finn.entity.ArticleTag;
import com.finn.exception.GlobalException;
import com.finn.exception.MyRuntimeException;
import com.finn.mapper.ArticleMapper;
import com.finn.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finn.service.ArticleTagService;
import com.finn.utils.BeanCopyUtils;
import com.finn.vo.ArticleListVO;
import com.finn.vo.ArticleVO;
import com.finn.vo.DeleteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
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
    @Autowired
    private ArticleMapper articleMapper;

    /* 
    * @Description: 保存或修改文章 
    * @Param: [articleVO] 
    * @return: void 
    * @Author: Finn
    * @Date: 2022/02/05 16:19
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateArticle(ArticleVO articleVO) {
        String content = articleVO.getArticleContent(); // 内容
        String previewContent = content.length() < 201 ? content : content.substring(0, 201); // 预览内容

        Article article = Article.builder()
                .id(articleVO.getArticleId())
                .articleTitle(articleVO.getArticleTitle())
                .articleContent(content)
                .articlePreviewContent(previewContent)
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
                            .articleId(article.getId())
                            .tagId(tagId)
                            .build())
                    .collect(Collectors.toList());
            articleTagService.saveBatch(articleTagList);
        }
    }

    /*
    * @Description: 获取后台文章总数
    * @Param: []
    * @return: long
    * @Author: Finn
    * @Date: 2022/02/05 19:59
    */
    @Override
    public Integer countArticleBack(Boolean isShowPage) {
        return this.baseMapper.countArticleBack(isShowPage);
    }

    /*
    * @Description: 获取后台文章分页
    * @Param: [articleListVO]
    * @return: java.util.List<com.finn.dto.ArticleListPageBackDTO>
    * @Author: Finn
    * @Date: 2022/02/05 20:18
    */
    @Override
    public List<ArticleListPageBackDTO> listArticlePageBack(ArticleListVO articleListVO) {
        return this.baseMapper.listArticlePageBack(articleListVO);
    }

    /*
    * @Description: 获取后台文章分页，并返回给前端
    * @Param: [page, articleListVO]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.finn.dto.ArticleListPageBackDTO>
    * @Author: Finn
    * @Date: 2022/02/05 16:19
    */
    @Override
    public IPage<ArticleListPageBackDTO> listArticlePageBackDTO(ArticleListVO articleListVO) {
        // LIMIT #{articleListVO.current}, #{articleListVO.size}
        // 第一个参数是输出记录的初始位置，第二个参数偏移量
        articleListVO.setCurrent((articleListVO.getCurrent() - 1) * articleListVO.getSize()); // 初始位置[不包括]
        long total = this.countArticleBack(false);
        if(total == 0) return new IPage<>();
        List<ArticleListPageBackDTO> list = listArticlePageBack(articleListVO);
        return new IPage<>(list, total);
    }

    /* 
    * @Description: 置顶一篇文章
    * @Param: [articleId, isTop] 
    * @return: void 
    * @Author: Finn
    * @Date: 2022/02/05 16:23
    */
    @Override
    public void topArticleById(Integer articleId, Integer isTop) {
//        Article article = Article.builder().id(articleId).isTop(isTop).build();
//        this.articleMapper.updateById(article);
        this.baseMapper.topArticleById(articleId, isTop);
    }

    /*
     * @Description: 获取展示页文章列表
     * @Param: [articleListVO]
     * @return: java.util.List<com.finn.dto.ArticleListPageBackDTO>
     * @Author: Finn
     * @Date: 2022/02/05 20:17
     */
    @Override
    public List<ArticlePreviewPageDTO> listArticlePreview(ArticleListVO articleListVO) {
        return this.baseMapper.listArticlePreview(articleListVO);
    }

    /* 
    * @Description: 返回给前端展示页的文章列表 
    * @Param: [articleListVO] 
    * @return: com.finn.entity.IPage<com.finn.dto.ArticlePreviewPageDTO>
    * @Author: Finn
    * @Date: 2022/02/05 20:58
    */
    @Override
    public IPage<ArticlePreviewPageDTO> listArticlePreviewPageDTO(ArticleListVO articleListVO) {
        articleListVO.setCurrent((articleListVO.getCurrent() - 1) * articleListVO.getSize()); // 初始位置[不包括]
        long total = this.countArticleBack(true); // 仅统计非草稿的文章
        if(total == 0) return new IPage<>();
        List<ArticlePreviewPageDTO> list = listArticlePreview(articleListVO);
        return new IPage<>(list, total);
    }

    /* 
    * @Description: 展示文章内容 
    * @Param: [articleId] 
    * @return: java.util.List<com.finn.dto.ArticleDTO>
    * @Author: Finn
    * @Date: 2022/02/05 21:42
    */
    @Override
    public ArticleDTO getArticleById(Integer articleId) {

        CompletableFuture<List<ArticleRecommendDTO>> recommendArticleList = CompletableFuture
                .supplyAsync(() -> articleMapper.listRecommendArticles(articleId));
        // 查询最新文章
        CompletableFuture<List<ArticleRecommendDTO>> newestArticleList = CompletableFuture
                .supplyAsync(() -> {
                    List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                            .select(Article::getId, Article::getArticleTitle, Article::getArticleCover, Article::getCreateTime)
                            .eq(Article::getIsDelete, 0)
                            .orderByDesc(Article::getId)
                            .last("limit 5"));
                    return BeanCopyUtils.copyList(articleList, ArticleRecommendDTO.class);
                });
        // 查询id对应文章
        ArticleDTO articleDTO = articleMapper.getArticleById(articleId);
        if (Objects.isNull(articleDTO)) {
            throw new MyRuntimeException("文章不存在");
        }
        // 更新文章浏览量
        /* 更新浏览量操作 */
        // 查询上一篇下一篇文章
        Article preArticle = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle, Article::getArticleCover)
                .eq(Article::getIsDelete, 0)
                .lt(Article::getId, articleId)
                .orderByDesc(Article::getId)
                .last("limit 1"));
        Article nextArticle = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle, Article::getArticleCover)
                .eq(Article::getIsDelete, 0)
                .gt(Article::getId, articleId)
                .orderByAsc(Article::getId)
                .last("limit 1"));
        articleDTO.setPreArticle(BeanCopyUtils.copyObject(preArticle, ArticlePaginationDTO.class));
        articleDTO.setNextArticle(BeanCopyUtils.copyObject(nextArticle, ArticlePaginationDTO.class));
        // 封装点赞量和浏览量
        articleDTO.setViewsCount(0);
        // 封装文章信息
        try {
            articleDTO.setRecommendArticleList(recommendArticleList.get());
            articleDTO.setNewestArticleList(newestArticleList.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDTO;
    }

    /*
    * @Description: 逻辑删除和恢复文章
    * @Param: [deleteVO]
    * @return: void
    * @Author: Finn
    * @Date: 2022/02/07 12:04
    */
    @Override
    public void recoverOrDeleteArticle(DeleteVO deleteVO) {
        List<Article> articleList = deleteVO.getIdList()
                .stream()
                .map(id -> Article.builder()
                        .id(id)
                        .isTop(0)
                        .isDelete(deleteVO.getIsDelete())
                        .build()
                ).collect(Collectors.toList());
        articleService.updateBatchById(articleList);
    }
}

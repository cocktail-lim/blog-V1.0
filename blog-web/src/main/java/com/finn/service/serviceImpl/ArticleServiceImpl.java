package com.finn.service.serviceImpl;

import com.finn.entity.Article;
import com.finn.mapper.ArticleMapper;
import com.finn.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

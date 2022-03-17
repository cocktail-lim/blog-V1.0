package com.finn.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.finn.dto.BlogInfoDTO;
import com.finn.service.*;
import com.finn.vo.WebsiteConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.finn.constant.RedisPrefixConst.WEBSITE_CONFIG;

/*
 * @description:
 * @author: Finn
 * @create: 2022/02/06 20:46
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private PageService pageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WebsiteConfigService websiteConfigService;

    @Override
    public BlogInfoDTO getBlogInfo() {

        return BlogInfoDTO.builder()
                .articleCount(articleService.countArticleBack(true))
                .categoryCount((int)categoryService.count())
                .tagCount((int)tagService.count())
                .viewsCount(null)
                .websiteConfig(this.getWebsiteConfig())
                .pageList(pageService.listPages())
                .build()
                ;
    }

    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO websiteConfigVO = null;
        Object websiteConfig = redisService.get(WEBSITE_CONFIG);
        if(Objects.nonNull(websiteConfig)) {
            websiteConfigVO = JSON.parseObject(websiteConfig.toString(), WebsiteConfigVO.class);
        } else {
            String config = websiteConfigService.getConfig();
            websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
            redisService.set(WEBSITE_CONFIG, config);
        }

        return websiteConfigVO;
    }
}

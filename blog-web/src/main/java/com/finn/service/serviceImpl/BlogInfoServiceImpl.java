package com.finn.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.finn.dto.BlogInfoDTO;
import com.finn.service.*;
import com.finn.utils.IpUtils;
import com.finn.vo.WebsiteConfigVO;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.finn.constant.CommonConst.*;
import static com.finn.constant.RedisPrefixConst.*;

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
    @Resource
    private HttpServletRequest request;

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

    @Override
    public void report() {
        // ??????ip
        String ipAddress = IpUtils.getIpAddress(request);
        // ??????????????????
        UserAgent userAgent = IpUtils.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // ????????????????????????
        String uuid = ipAddress + browser.getName() + operatingSystem.getName();
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        // ??????????????????
        if (!redisService.sIsMember(UNIQUE_VISITOR, md5)) {
            // ????????????????????????
            String ipSource = IpUtils.getIpSource(ipAddress);
            if (StringUtils.isNotBlank(ipSource)) {
                ipSource = ipSource.substring(0, 2)
                        .replaceAll(PROVINCE, "")
                        .replaceAll(CITY, "");
                redisService.hIncr(VISITOR_AREA, ipSource, 1L);
            } else {
                redisService.hIncr(VISITOR_AREA, UNKNOWN, 1L);
            }
            // ?????????+1
            redisService.incr(BLOG_VIEWS_COUNT, 1);
            // ??????????????????
            redisService.sAdd(UNIQUE_VISITOR, md5);
        }
    }
}

package com.finn.service.serviceImpl;

import com.finn.dto.HomeInfoDTO;
import com.finn.service.BlogInfoService;
import com.finn.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
 * @description:
 * @author: Finn
 * @create: 2022/02/06 20:46
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private PageService pageService;

    @Override
    public HomeInfoDTO getHomeInfo() {
        return HomeInfoDTO.builder()
                .articleCount(null)
                .categoryCount(null)
                .tagCount(null)
                .viewsCount(null)
                .pageList(pageService.listPages())
                .build()
                ;
    }
}

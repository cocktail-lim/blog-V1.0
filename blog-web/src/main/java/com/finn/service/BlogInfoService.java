package com.finn.service;

import com.finn.dto.BlogInfoDTO;
import com.finn.vo.WebsiteConfigVO;

/*
 * @description: 首页
 * @author: Finn
 * @create: 2022/02/06 20:46
 */
public interface BlogInfoService {

    /* 
    * @Description: 获取首页内容
    * @Param: [] 
    * @return: com.finn.dto.BlogInfoDTO
    * @Author: Finn
    * @Date: 2022/03/04 21:26
    */
    BlogInfoDTO getBlogInfo();

    /*
    * @Description: 获取网站配置信息
    * @Param:
    * @return:
    * @Author: Finn
    * @Date: 2022/03/04 23:04
    */
    WebsiteConfigVO getWebsiteConfig();
}

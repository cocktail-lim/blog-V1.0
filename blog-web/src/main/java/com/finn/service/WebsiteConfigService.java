package com.finn.service;

import com.finn.entity.WebsiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author finn
 * @since 2022-03-04
 */
public interface WebsiteConfigService extends IService<WebsiteConfig> {

    /*
    * @Description: 获取配置字段
    * @Param:
    * @return:
    * @Author: Finn
    * @Date: 2022/03/04 23:23
    */
    String getConfig();
}

package com.finn.service;

import com.finn.dto.PageDTO;
import com.finn.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 页面 服务类
 * </p>
 *
 * @author finn
 * @since 2022-02-06
 */
public interface PageService extends IService<Page> {

    /*
    * @Description: 获取show page页面List
    * @Param: []
    * @return: java.util.List<com.finn.dto.PageDTO>
    * @Author: Finn
    * @Date: 2022/02/06 20:24
    */
    List<PageDTO> listPages();
}

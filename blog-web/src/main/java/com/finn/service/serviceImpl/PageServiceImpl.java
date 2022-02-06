package com.finn.service.serviceImpl;

import com.finn.dto.PageDTO;
import com.finn.entity.Page;
import com.finn.mapper.PageMapper;
import com.finn.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 页面 服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-02-06
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

    /*
     * @Description: 获取show page页面List
     * @Param: []
     * @return: java.util.List<com.finn.dto.PageDTO>
     * @Author: Finn
     * @Date: 2022/02/06 20:24
     */
    @Override
    public List<PageDTO> listPages() {
        return this.baseMapper.listPages();
    }
}

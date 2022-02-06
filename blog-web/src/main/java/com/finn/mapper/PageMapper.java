package com.finn.mapper;

import com.finn.dto.PageDTO;
import com.finn.entity.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 页面 Mapper 接口
 * </p>
 *
 * @author finn
 * @since 2022-02-06
 */
@Mapper
public interface PageMapper extends BaseMapper<Page> {

    /*
     * @Description: 获取show page页面List
     * @Param: []
     * @return: java.util.List<com.finn.dto.PageDTO>
     * @Author: Finn
     * @Date: 2022/02/06 20:24
     */
    List<PageDTO> listPages();
}

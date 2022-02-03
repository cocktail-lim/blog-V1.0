package com.finn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finn.entity.ApiRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @description:
 * @author: Finn
 * @create: 2022/01/30 18:28
 */
@Mapper
public interface ApiRoleMapper extends BaseMapper<ApiRole> {
    List<ApiRole> getApiRole();
}

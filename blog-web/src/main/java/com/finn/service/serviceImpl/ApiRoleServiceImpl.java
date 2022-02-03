package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finn.entity.ApiRole;
import com.finn.mapper.ApiRoleMapper;
import com.finn.service.ApiRoleService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: UrlRoleService实现类
 * @author: Finn
 * @create: 2022/01/30 18:27
 */
@Service
public class ApiRoleServiceImpl extends ServiceImpl<ApiRoleMapper, ApiRole>  implements ApiRoleService {

    @Override
    public List<ApiRole> getApiRole() {
        return this.baseMapper.getApiRole();
    }
}

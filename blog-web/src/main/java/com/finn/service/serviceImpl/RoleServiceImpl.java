package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finn.dto.RoleSelectListDTO;
import com.finn.entity.Role;
import com.finn.mapper.RoleMapper;
import com.finn.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /* 
    * @Description: 用户角色下拉列表选项 角色名 个数
    * @Param: [] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/1/27 
    */
    @Override
    public List<RoleSelectListDTO> getUserRoleSelectList() {
        return this.baseMapper.getUserRoleSelectList();
    }

}

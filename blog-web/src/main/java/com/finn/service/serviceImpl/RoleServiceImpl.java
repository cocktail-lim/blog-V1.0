package com.finn.service.serviceImpl;

import com.finn.entity.Role;
import com.finn.mapper.RoleMapper;
import com.finn.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

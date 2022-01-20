package com.finn.service.serviceImpl;

import com.finn.entity.User;
import com.finn.mapper.UserMapper;
import com.finn.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*
    * @Description: 返回所有角色
    * @Param: []
    * @return: List<String>
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    public List<String> listUserRolesByUsername(String username) {
        return this.baseMapper.listUserRolesByUsername(username);
    }
}

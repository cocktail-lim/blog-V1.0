package com.finn.service;

import com.finn.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Service
public interface UserService extends IService<User> {

    boolean checkLogin(String username, String password);

    List<String> listUserRolesByUsername(String name);

}

package com.finn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.UserListPageDTO;
import com.finn.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finn.vo.UserQueryVO;
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

    List<UserListPageDTO> getUserListTest(String nickname);

    /*
     * @Description: 根据条件查找用户信息
     * @Param: [page, userQueryVO]
     * @return:
     * @Author: Finn
     * @Date: 2022/1/29
     */
    IPage<UserListPageDTO> getUserList(Page<UserListPageDTO> page, UserQueryVO userQueryVO);
}

package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.UserDTO;
import com.finn.dto.UserListPageDTO;
import com.finn.entity.User;
import com.finn.mapper.UserMapper;
import com.finn.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finn.vo.UserQueryVO;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*
    * @Description: test
    * @Param: [nickname]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/29
    */
    @Override
    public List<UserListPageDTO> getUserListTest(String nickname) {
        return this.baseMapper.getUserListTest(nickname);
    }

    /*
    * @Description: 登录检查
    * @Param: [username, password]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/20
    */
    @Override
    public boolean checkLogin(String username, String password) {
        return true;
    }


    /*
    * @Description: 根据username查询其角色
    * @Param: []
    * @return: List<String>
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
        public List<String> listUserRolesByUsername(String username) {
        return this.baseMapper.listUserRolesByUsername(username);
    }

    /*
     * @Description: 根据条件查询用户信息
     * @Param: [page, userQueryVO]
     * @return:
     * @Author: Finn
     * @Date: 2022/1/29
     */
    @Override
    public IPage<UserListPageDTO> getUserList(Page<UserListPageDTO> page, UserQueryVO userQueryVO) {
        return this.baseMapper.getUserList(page, userQueryVO);
    }
}

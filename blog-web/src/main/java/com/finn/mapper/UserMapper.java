package com.finn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.UserListPageDTO;
import com.finn.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finn.vo.UserQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*
    * @Description: 根据 username 查找 user 的 roles
    * @Param: [name]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/19
    */
    List<String> listUserRolesByUsername(@Param("username") String name);

    IPage<UserListPageDTO> getUserListPage(Page<UserListPageDTO> page, @Param("roleName") String roleName, @Param("nickname") String nickname);

    /*
    * @Description: 根据条件（用户角色/昵称）来查询用户信息
    * @Param: [userQueryVO]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/29
    */
    List<UserListPageDTO> getUserByCondition(@Param("userQueryVO") UserQueryVO userQueryVO);

    List<UserListPageDTO> getUserListTest(@Param("nickname") String nickname);
}

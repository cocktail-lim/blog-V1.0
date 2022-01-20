package com.finn.mapper;

import com.finn.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}

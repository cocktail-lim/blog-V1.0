package com.finn.mapper;

import com.finn.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

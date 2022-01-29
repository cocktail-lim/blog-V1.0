package com.finn.mapper;

import com.finn.dto.RoleSelectListDTO;
import com.finn.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleSelectListDTO> getUserRoleSelectList();
}

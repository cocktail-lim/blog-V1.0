package com.finn.mapper;

import com.finn.dto.ResourceRoleDTO;
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

    /* 
    * @Description:
    * @Param: [] 
    * @return: java.util.List<com.finn.dto.RoleSelectListDTO> 
    * @Author: Finn
    * @Date: 2022/03/16 00:05
    */
    List<RoleSelectListDTO> getUserRoleSelectList();

    /* 
    * @Description: 查询路由角色列表 
    * @Param: [] 
    * @return: java.util.List<com.finn.dto.ResourceRoleDTO> 
    * @Author: Finn
    * @Date: 2022/03/15 23:52
    */
    List<ResourceRoleDTO> listResourceRoles();

    /* 
    * @Description: 根据用户id获取角色列表 
    * @Param: [userInfoId] 
    * @return: java.util.List<java.lang.String> 
    * @Author: Finn
    * @Date: 2022/03/15 23:52
    */
    List<String> listRolesByUserInfoId(Integer userInfoId);
}
